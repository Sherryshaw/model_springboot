package com.project.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class EncodeUtil {
    private static final String salt=new SecureRandomNumberGenerator().nextBytes().toString();
    private static final int times=2;
    private static final String alogrithmName="md5";
    private EncodeUtil(){}
    public static String encode(String password){
        return new SimpleHash(alogrithmName,password,salt,times).toString();
    }
    public static String encode(String password,String salt){
        return new SimpleHash(alogrithmName,password,salt,times).toString();
    }
    public static String getSalt() {
        return salt;
    }
}
