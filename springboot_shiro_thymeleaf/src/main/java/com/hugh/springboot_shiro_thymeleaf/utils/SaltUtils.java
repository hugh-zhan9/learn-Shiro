package com.hugh.springboot_shiro_thymeleaf.utils;

import org.apache.shiro.util.SimpleByteSource;

import java.util.Random;

/**
 * Created by hugh on 2021/1/1
 */
public class SaltUtils {
    public static String getSalt(int n){
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()".toCharArray();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(SaltUtils.getSalt(4));
    }
}
