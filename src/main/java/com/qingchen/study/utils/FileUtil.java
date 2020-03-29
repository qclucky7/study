/**
 * Copyright (c) 2016 21CN.COM . All rights reserved.<br>
 *
 * Description: calendar<br>
 * 
 * Modified log:<br>
 * ------------------------------------------------------<br>
 * Ver.		Date		Author			Description<br>
 * ------------------------------------------------------<br>
 * 1.0		2016年3月8日		kexm		created.<br>
 */
package com.qingchen.study.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

/**
 * 文件工具类
 * @author jianghui
 *
 */
public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * 远程文件是否存在
	 * @param fileServerUrl
	 * @return
	 * @throws IOException
	 */
	public static boolean isFileExitsInServer(String fileServerUrl) throws IOException  {
		if (StringUtils.isEmpty(fileServerUrl)) {
			return false;
		}
		URL serverUrl = new URL(fileServerUrl);
        HttpURLConnection urlcon = (HttpURLConnection) serverUrl.openConnection();
        int code = urlcon.getResponseCode();
        urlcon.disconnect();
        return code == HttpURLConnection.HTTP_OK;
	}
	
	public static void main(String[] args) throws IOException {
		logger.info("{}",isFileExitsInServer("https://alumni.ckgsb.com/file/upload/2018/03/13/519b20913f5ab977c36edba2f632576c"));
	}
	
	 /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,String newstr) throws IOException{
        Boolean bool = false;
        //新写入的行，换行
        String filein = newstr+"\r\n";
        String temp  = "";
        
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
        		//文件路径(包括文件名称)
            File file = new File(filepath);
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            
            //文件原有内容
			for (int i = 0; (temp = br.readLine()) != null; i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);
            
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }
    
	public static void saveFile(String filePath, byte[] data) throws Exception {
		if (data != null) {
			File file = new File(filePath);
			if (file.exists()) {
				file.delete();
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data, 0, data.length);
			fos.flush();
			fos.close();
		}
	}
    
	/**
	 * 获取文件md5
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (file == null) {
			logger.info("file null");
			return null;
		}
		if (!file.isFile()) {
			logger.info("not file");
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte[] buffer = new byte[8192];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 从inputStream中读，且写到某个路径文件下（带文件名的全路径）
	 * @param inputStream
	 * @param path
	 * @throws IOException
	 */
	public static void writeContentFromInputStream(InputStream inputStream, String path) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(path);
		byte[] data = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(data)) != -1) {
			outputStream.write(data, 0, len);
		}
		inputStream.close();
		outputStream.close();
	}

	/**
	 * 复制文件
	 * @param fromFile 源文件
	 * @param toFile 目标文件
	 * @throws IOException
	 */
	public static void copyFile(File fromFile, File toFile) throws IOException {
		FileInputStream ins = new FileInputStream(fromFile);
		FileOutputStream out = new FileOutputStream(toFile);
		byte[] b = new byte[1024];
		int n = 0;
		while ((n = ins.read(b)) != -1) {
			out.write(b, 0, n);
		}
		ins.close();
		out.close();
	}

	/**
	 * 一次性读取小文件的所有数据
	 * @param fileName
	 * @return
	 */
	public static String readToStringForOnce(String fileName) {
		String encoding = "UTF-8";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}
	
}
