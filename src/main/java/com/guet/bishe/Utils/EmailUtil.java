package com.guet.bishe.Utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: zfl
 * @Date: 2023-12-01-15:58
 * @Description:
 */
@Component
@Slf4j
public class EmailUtil {
    public static final TimedCache<String, Integer> timedCache = CacheUtil.newTimedCache(60 * 1000);
    @Value("${email.code:}")
    private String code;
    @Value("${email.user:}")
    private String user;

    public void sendRegisterEmail(String email) {
        int randomInt = RandomUtil.randomInt(100_000, 999_999);
        log.info("验证码:{}", randomInt);
        timedCache.put(email, randomInt);
        ThreadUtil.execAsync(new Runnable() {
            @Override
            public void run() {
                // 发送邮件
                JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
                javaMailSender.setHost("smtp.qq.com");
                javaMailSender.setPort(465);
                javaMailSender.setUsername(user);
                javaMailSender.setPassword(code);
                javaMailSender.setDefaultEncoding("UTF-8");

                Properties properties = new Properties();
                properties.setProperty("mail.smtp.timeout", "30000");
                properties.setProperty("mail.smtp.auth", "true");
                properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                javaMailSender.setJavaMailProperties(properties);

                // 构建一个邮件对象
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                // true表示构建一个可以带附件的邮件对象
                try {
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                    // 设置邮件主题
                    mimeMessageHelper.setSubject("验证码邮件");
                    // 设置邮件发送者
                    mimeMessageHelper.setFrom(user);
                    // 设置邮件接收者，可以有多个接收者
                    mimeMessageHelper.addTo(email);
                    // 设置邮件发送日期
                    mimeMessageHelper.setSentDate(new Date());
                    // 设置邮件的正文
                    mimeMessageHelper.setText(StrUtil.format("<p>您的验证码：{}，有效期10分钟</p>", randomInt, true));
                    javaMailSender.send(mimeMessage);
                } catch (MessagingException e) {
                    log.error("待审批邮件发送失败", e);
                }
            }
        });
    }

    public void sendSuccess(String[] emails, String examName, String lessonName) {
        // 发送邮件
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername(user);
        javaMailSender.setPassword(code);
        javaMailSender.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.timeout", "30000");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(properties);

        // 构建一个邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // true表示构建一个可以带附件的邮件对象
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 设置邮件主题
            mimeMessageHelper.setSubject("考试结束");
            // 设置邮件发送者
            mimeMessageHelper.setFrom(user);
            // 设置邮件接收者，可以有多个接收者
            mimeMessageHelper.setTo(emails);
            // 设置邮件发送日期
            mimeMessageHelper.setSentDate(new Date());
            // 设置邮件的正文
            String emailContent = lessonName + "的" + examName + "的考试已经结束了，请去查询你的成绩。\n<a href=\"http://localhost:9999/\">点击这里</a>查看。";
            mimeMessageHelper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("待审批邮件发送失败", e);
        }
    }

    public void sendSuccessTeacher(String[] emails, String examName, String lessonName) {
        // 发送邮件
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername(user);
        javaMailSender.setPassword(code);
        javaMailSender.setDefaultEncoding("UTF-8");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.timeout", "30000");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailSender.setJavaMailProperties(properties);

        // 构建一个邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // true表示构建一个可以带附件的邮件对象
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 设置邮件主题
            mimeMessageHelper.setSubject("考试开始批阅");
            // 设置邮件发送者
            mimeMessageHelper.setFrom(user);
            // 设置邮件接收者，可以有多个接收者
            mimeMessageHelper.setTo(emails);
            // 设置邮件发送日期
            mimeMessageHelper.setSentDate(new Date());
            // 设置邮件的正文
            String emailContent = lessonName + "的" + examName + "的考试已经开始批改了，请前往批改。\n<a href=\"http://localhost:9999/\">点击这里</a>批改。";
            mimeMessageHelper.setText(emailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("待审批邮件发送失败", e);
        }
    }

}
