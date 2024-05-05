package com.guet.bishe.Utils;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author cardo
 * @Version 1.0
 * @Description TODO
 * @date 2024/5/5 22:40
 */
public class MD5 {

    /** * MD5加密之方法三 * @explain springboot自带MD5加密 * @param str * 待加密字符串 * @return 16进制加密字符串 */
    public static String encrypt3ToMD5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}


