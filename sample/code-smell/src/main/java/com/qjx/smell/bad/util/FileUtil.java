package com.qjx.smell.bad.util;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 如果需要追加写入，则使用FileWriter的构造器传入true
 *
 * @author qinjiaxing on 2023/4/10
 * @author <others>
 */
public class FileUtil {

    public static void main(String[] args) {
        String filepath = "/Users/qinjiaxing/code/casin/repeatable/sample/basic-sample/src/main/java/com/qjx/sample/util/b.txt";
        String context = "hello world";
        try {
            bufferedFileOutputStreamMethod(filepath, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法一：使用FileWrite 写文件
     *
     * @param filepath
     * @param context
     * @throws IOException
     */
    public static void fileWriteMethod(String filepath, String context) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.append(context);
        }
    }

    /**
     * 文件最快
     * 使用 BufferedWriter 写文件
     *
     * @param filepath
     * @param context
     * @throws IOException
     */
    public static void bufferedWriteMethod(String filepath, String context) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(context);
        }
    }

    /**
     * 二进制最快
     * 使用流写入文件
     *
     * @param filepath
     * @param context
     * @throws IOException
     */
    public static void fileOutputStreamMethod(String filepath, String context) throws IOException {
        try (FileOutputStream os = new FileOutputStream(filepath)) {
            os.write(context.getBytes());
        }
    }

    /**
     * 流 + 缓冲区写入
     *
     * @param filepath
     * @param context
     * @throws IOException
     */
    public static void bufferedFileOutputStreamMethod(String filepath, String context) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filepath))) {
            bos.write(context.getBytes());
        }
    }


    /**
     * 最慢
     * 使用Files 写文件
     * @param filepath
     * @param context
     * @throws IOException
     */
    public void filesMethod(String filepath, String context) throws IOException {
        Files.write(Paths.get(filepath), context.getBytes());
    }



}
