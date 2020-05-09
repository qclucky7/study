package com.qingchen.sdk.file;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName FilesAndPathsTest
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 16:26
 **/
public class FilesAndPathsTest {



    @Test
    public void myTest() throws Exception{

        Path path = Files.walkFileTree(Paths.get("D:\\file"), new FileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });


        Stream<Path> walk = Files.walk(Paths.get("D:\\file"), FileVisitOption.FOLLOW_LINKS);
        List<Path> collect = walk.collect(Collectors.toList());

        System.out.println(collect.toString());

        System.out.println(path);

    }
}
