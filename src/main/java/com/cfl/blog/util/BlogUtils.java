package com.cfl.blog.util;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Administer
 * 工具类
 */
public class BlogUtils {


    public static List<String> makeTags(String source){

        String[] split = null;
        if (!"".equals(source)&& source!=null ){
             split = source.split("-");
        }
        return Arrays.asList(split);
    }



    /**
     * 拼接图片在服务器上的地址
     * @param port
     * @return
     */
    public static String getAddr(String port){

        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://" + hostAddress + ":" + port + "/";

    }



    public static String makeFileName(String fileName){

        String name = "";
        String firstName = UUID.randomUUID().toString().replaceAll("-", "");
        // 获取文件后缀名
        String lastName = fileName.split("\\.")[1];

        name = firstName + "." +  lastName;


        return name;
    }




    /**
     * md5加密
     * @param source 明文
     * @return encoding 密文
     */
    public static String md5(String source) {

        // 1.判断source是不是空
        if (source == null || source.length() == 0) {
            // 2.提示字符串不合法
            throw new RuntimeException(ConstFiled.MESSAGE_PARAMS_ILLEGAL);

        }

        try {
            // 3.获取MessageDigest对象
            String algorithm = "md5";
            MessageDigest md = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符串的字节数组
            byte[] input = source.getBytes();

            // 5.加密
            byte[] output = md.digest(input);

            // 6.创建BigInteger对象
            //-1 for negative, 0 for zero, or 1 for positive
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum,output);

            // 7.按照16进制将bigInteger值转换成字符串
            //radix
            int radix = 16;
            String encoding = bigInteger.toString(radix);
            return encoding;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }



}
