package com.afagoal.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by BaoCai on 2019/3/29. Description:
 */
public class UserContext {

    public static final AfaUser getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes()).getRequest();
        String afaUid = request.getHeader("afa-uid");
        Long id = StringUtils.isBlank(afaUid) ? 0 : Long.valueOf(afaUid);

        String name = request.getHeader("afa-name");
        String address = request.getHeader("afa-address")
            ;
        if (StringUtils.isNotBlank(name)) {
            try {
                name = URLDecoder.decode(name, "utf-8");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
            }
        }

        return new AfaUser(id == null ? 0 : id, name == null ? "" : name,
            address == address ? "" : address);
    }

}
