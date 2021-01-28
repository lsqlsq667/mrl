package com.lsq.ssm.blog.util;

import com.cwn.wethink.pojo.dto.MailParamsDTO;
import com.cwn.wethink.services.SystemSettingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailUtils {

    private static SystemSettingService systemSettingService;

    public EmailUtils(SystemSettingService systemSettingService) {
        EmailUtils.systemSettingService = systemSettingService;
    }

    /**
     * 邮件封装发送类
     */
    public static MailParamsDTO getMailParams() {
        return EmailUtils.systemSettingService.getMailParams();
    }

    /**
     * 邮件实体
     *
     * @param smtpHost     邮件服务器地址
     * @param sendUserName 发件邮件地址
     * @param sendUserPass 发件邮箱密码
     * @param to           收件人，多个邮箱地址以半角逗号分隔
     * @param cc           抄送，多个邮箱地址以半角逗号分隔
     * @param mailSubject  邮件主题
     * @param mailBody     邮件正文
     * @param attachments  附件路径
     * @return
     */
    public static boolean send(String smtpHost, String sendUserName, String sendUserPass,
                               String to, String cc, String mailSubject, String mailBody,
                               List<String> attachments) {
        Email email = Email.entity(smtpHost, sendUserName, sendUserPass, to, cc,
                mailSubject, mailBody, attachments);
        boolean flag = email.send(); // 发送！
        email = null;
        return flag;
    }

    /**
     * 邮件实体
     *
     * @param smtpHost     邮件服务器地址
     * @param sendUserName 发件邮件地址
     * @param sendUserPass 发件邮箱密码
     * @param to           收件人，多个邮箱地址以半角逗号分隔
     * @param cc           抄送，多个邮箱地址以半角逗号分隔
     * @param mailSubject  邮件主题
     * @param mailBody     邮件正文
     * @return
     */
    public static boolean send(String smtpHost, String sendUserName, String sendUserPass,
                               String to, String cc, String mailSubject, String mailBody) {
        Email email = Email.entity(smtpHost, sendUserName, sendUserPass, to, cc,
                mailSubject, mailBody, null);
        boolean flag = email.send(); // 发送！
        email = null;
        return flag;
    }

    /**
     * 邮件实体
     *
     * @param to          收件人，多个邮箱地址以半角逗号分隔
     * @param cc          抄送，多个邮箱地址以半角逗号分隔
     * @param mailSubject 邮件主题
     * @param mailBody    邮件正文
     * @param attachments 附件路径
     * @return
     */
    public static boolean send(String to, String cc, String mailSubject, String mailBody,
                               List<String> attachments) {
        MailParamsDTO param = getMailParams();
        String host = param.getHost();
        String username = param.getSendName();
        String password = param.getPassword();
        Email email = Email.entity(host, username, password, to, cc,
                mailSubject, mailBody, attachments);
        boolean flag = email.send(); // 发送！
        email = null;
        return flag;
    }

    /**
     * 邮件实体
     *
     * @param to          收件人，多个邮箱地址以半角逗号分隔
     * @param cc          抄送，多个邮箱地址以半角逗号分隔
     * @param mailSubject 邮件主题
     * @param mailBody    邮件正文
     * @return
     */
    public static boolean send(String to, String cc, String mailSubject, String mailBody) {
        MailParamsDTO param = getMailParams();
        String host = param.getHost();
        String username = param.getSendName();
        String password = param.getPassword();
        Email email = Email.entity(host, username, password, to, cc,
                mailSubject, mailBody, null);
        boolean flag = email.send(); // 发送！
        email = null;
        return flag;
    }

}
