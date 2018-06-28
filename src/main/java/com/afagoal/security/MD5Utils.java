package com.afagoal.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by BaoCai on 18/4/13.
 * Description:
 */
public class MD5Utils {

    private static final String AFAGOAL_KEY = "afagoal_";

    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static String passwordSecurcy(String password) {
        return md5Hex(password + AFAGOAL_KEY);
    }

}
