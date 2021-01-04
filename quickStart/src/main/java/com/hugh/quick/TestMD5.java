package com.hugh.quick;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMD5 {
    public static void main(String[] args) {
        // 不推荐使用，这种方式并没进行加密
        Md5Hash md5 = new Md5Hash();
        md5.setBytes("19961220".getBytes());
        System.out.println(md5.toHex());

        // 推荐使用的方式：通过构造方法赋值进行加密
        Md5Hash md5Hash = new Md5Hash("19961220zyk.");
        System.out.println(md5Hash.toHex());

        // 使用MD5加盐处理，一般加的盐使用随机生成并和数据一起存入数据库
        Md5Hash md5HashSalt = new Md5Hash("19961220","zyk.");
        System.out.println(md5HashSalt.toHex());

        // 使用hash散列，第三个参数表示进行散列的次数,一般使用1024的倍数
        Md5Hash md5HashSalt1 = new Md5Hash("19961220","zyk.",1024);
        System.out.println(md5HashSalt1);
    }
}
