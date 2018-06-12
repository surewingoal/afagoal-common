package com.afagoal.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.Setter;

/**
 * Created by BaoCai on 18/6/11.
 * Description:
 */
@Setter
public class AfagoalMainSender {

    private JavaMailSender sender;
    private String organization;
    private String from;
    private String baseContent;

    public AfagoalMainSender(JavaMailSender sender) {
        Assert.notNull(sender, "mailSender can not be null!");
        this.sender = sender;
    }

    public void send(String content, String to, String subject) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(this.from, this.organization);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content + this.baseContent, true);
        //发送邮件
        sender.send(mimeMessage);
    }

}
