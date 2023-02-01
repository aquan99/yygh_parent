package com.wuwq.yygh.common.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.common.util
 * @ClassName:MD5Utils
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-21 10:36
 * @Version: 1.0
 */
public class MD5Utils {

    public static String encode(String srcStr) {
        return DigestUtils.md5Hex(srcStr + srcStr.substring(srcStr.length() - 3)).substring(0,30);
    }

    public static boolean verify(String srcStr, String password) {
        return encode(srcStr).equals(password);
    }
}
