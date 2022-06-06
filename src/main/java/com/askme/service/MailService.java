package com.askme.service;

import javax.mail.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.askme.exceptions.askmeException;
import com.askme.model.NotificationEmail;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	@Async
	public  void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("info@bestofbest.in");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
           // messageHelper.setContent(mailContentBuilder.build(notificationEmail.getSubject(),"text/html"));
           
        };
        try {
            mailSender.send(messagePreparator);
            //log.info("Activation email sent!!");
        } catch (MailException e) {
           // log.error("Exception occurred when sending mail", e);
            throw new askmeException("Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
        }
	}
}
