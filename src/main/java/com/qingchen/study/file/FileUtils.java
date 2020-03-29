package com.qingchen.study.file;

import com.qingchen.study.utils.StringUtils;
import com.qingchen.study.vlife.ErrorCode;
import com.qingchen.study.vlife.ErrorCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName FileUpload
 * @description:
 * @author: WangChen
 * @create: 2020-03-23 14:21
 **/
@RestController
@RequestMapping("/file")
public class FileUtils{

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 单文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()){
            logger.error("[FileUpload][The current file does not exist]");
            throw new ErrorCodeException(ErrorCode.file_isNot_exist);
        }
        Resource resource = file.getResource();
        String fileName = resource.getFilename();
        if (StringUtils.isEmpty(fileName)){
            logger.error("[FileUpload][The current filename does not exist]");
            throw new ErrorCodeException(ErrorCode.file_isNot_exist);
        }

        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") == -1 ?
                0 : fileName.lastIndexOf(".")).toUpperCase();

        Path filePath = Paths.get("D:\\" + UUID.randomUUID().toString().substring(0, 8) + fileSuffix);

        Path path = Files.write(filePath, file.getBytes());

        return path.toString();
    }

    /**
     * 批量上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/batch_upload")
    public List<String> batchUpload(@RequestParam("file") MultipartFile[] file) throws IOException {

        List<String> filePaths = new LinkedList<>();
        for (MultipartFile multipartFile : file) {
            String filePath = upload(multipartFile);
            filePaths.add(filePath);
        }
        return filePaths;
    }

    @PostMapping("/download")
    public String fileDownload(@RequestBody FileEntity fileEntity, HttpServletResponse response) throws IOException{

        System.out.println("fileUrl = " + fileEntity.getFileUrl());

        Path path = Paths.get(fileEntity.getFileUrl());

        System.out.println("path = " +path.toString());

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(path));

        return "success";

    }

    static class FileEntity{

        private String fileUrl;

        public String getFileUrl() {
            return fileUrl;
        }

    }


    private static byte[] byte_size = new byte[8192];


    /**
     * 压缩文件或路径
     * @param zipPath 压缩包路径
     * @param srcFile 压缩的源文件
     * @return
     */
    public static boolean zipFile(String zipPath, File srcFile) {
        if (zipPath == null || srcFile == null) {
            logger.error("invalid zip name or zip file content");
            return false;
        }
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(zipPath))))
        {
            compressFile(zipOutputStream, srcFile, srcFile.getName());
            return true;
        } catch (IOException e) {
            logger.error("", e);
        }
        return false;
    }


    /**
     * 递归压缩文件
     * @param out
     * @param sourceFile
     * @param base
     */
    private static void compressFile(ZipOutputStream out, File sourceFile, String base) throws IOException {
        if (out == null || sourceFile == null || !sourceFile.exists() || StringUtils.isEmpty(base)) {
            logger.error("compressFile invalid parameters");
            return;
        }
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            if (files == null || files.length == 0) {
                out.putNextEntry(new ZipEntry(base + "/"));
                out.closeEntry();
            } else {
                for (File file : files) {
                    compressFile(out, file, base + "/" + file.getName());
                }
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            int len = 0;
            while ((len = fileInputStream.read(byte_size)) > 0) {
                out.write(byte_size, 0, len);
            }
            fileInputStream.close();
            out.closeEntry();
        }
    }


    public static void main(String[] args) throws Exception{

        File file = new File("D:\\1.zip");


        File file1 = new File("D:\\Huawei Share\\OneHop\\my1\\3.jpeg");
        System.out.println(file1.length());
        //zipFile("D:\\1.zip", file1);

        RandomAccessFile randomAccessFile = new RandomAccessFile(file1, "rw");

        long filePointer = randomAccessFile.getFilePointer();

        System.out.println(filePointer);

    }
}
