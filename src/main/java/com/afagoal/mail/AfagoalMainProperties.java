package com.afagoal.mail;

import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by BaoCai on 18/6/12.
 * Description:
 */
@Getter
@Setter
public class AfagoalMainProperties {

    //messagePart
    private String messageOrganization = "AFAGOAL虚拟币行情";
    private String messageFrom;
    private String messageBaseContent = "<div style='float:right;'><a class='btn  btn-primary btn-block' href=http://118.24.27.218:8080/login>联系我们</a></div>";

    //senderPart
    private String senderHost = "smtp.163.com";
    private int senderPost = 25;
    private String senderUsername;
    private String senderPassword;
    private String senderDefaultEncoding = "Utf-8";
    private Properties senderProperties = new Properties();
}
