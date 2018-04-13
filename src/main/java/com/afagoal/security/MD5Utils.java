package com.afagoal.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by BaoCai on 18/4/13.
 * Description:
 */
public class MD5Utils {

    public static String md5Hex(String data){
        return DigestUtils.md5Hex(data);
    }

}
