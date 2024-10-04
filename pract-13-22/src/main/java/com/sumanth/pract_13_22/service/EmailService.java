package com.sumanth.pract_13_22.service;

import com.sumanth.pract_13_22.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender javaMailSender;
   @Autowired
   public TemplateEngine templateEngine;

   public void sendPlainEmail(String fromEmail,String toEmail,String subject,String mailBody)
   {
       SimpleMailMessage message= new SimpleMailMessage();
       message.setFrom(fromEmail);
       message.setTo(toEmail);
       message.setSubject(subject);
       message.setText(mailBody);

       javaMailSender.send(message);
   }
    public void sendHtmlEmail(String fromEmail, String toEmail, String subject, String mailBody) throws Exception
    {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(mailBody, true);

        javaMailSender.send(message);
    }
    public void sendTemplateEmail(String fromEmail, String toEmail, String subject, String filename, User userdata) throws Exception
    {
        Context context= new Context();
        context.setVariable("name",userdata.getName());
        context.setVariable("link",userdata.getPasswordResetKey());
        System.out.println(userdata.getPasswordResetKey());
       String mailBody=templateEngine.process(filename,context);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(mailBody, true);

        javaMailSender.send(message);
    }

}
