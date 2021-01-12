package com.xiaoniu.fuse;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        Config config = new Config();
//        config.setUseLinuxNativeEpoll(true);
//        config.useClusterServers()
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://10.17.2.197:6379");
//
//        RedissonClient redisson = Redisson.create(config);
//        RMap<String, String> map = redisson.getMap("anyMap");
//        map.fastPut("321", "123");
        Integer a = 234;
        Integer b = 345;
        System.out.println(a < b);
        String a1 = "";
        a1.equals("");
    }


    public static String TestBase64Pic() throws IOException {
        File file = new File("C:\\Users\\xn066495\\Desktop\\timg.jpg");
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    public static byte[] compress(String string) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(string.length());
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(string.getBytes());
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
    }


    public static String decompress(byte[] compressed) throws IOException {
        final int BUFFER_SIZE = 32;
        ByteArrayInputStream is = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = gis.read(data)) != -1) {
            baos.write(data, 0, bytesRead);
        }
        gis.close();
        return baos.toString("UTF-8");
    }

}
