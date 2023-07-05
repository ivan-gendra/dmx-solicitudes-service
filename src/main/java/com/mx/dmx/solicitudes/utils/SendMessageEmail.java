package com.mx.dmx.solicitudes.utils;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.nlab.smtp.pool.SmtpConnectionPool;
import org.nlab.smtp.transport.connection.ClosableSmtpConnection;
import org.nlab.smtp.transport.factory.SmtpConnectionFactory;
import org.nlab.smtp.transport.factory.SmtpConnectionFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SendMessageEmail {

	private SmtpConnectionPool smtpConnectionPool;

	private ClosableSmtpConnection transport;

	private Integer mail1 = 0;

	@Value("${mail.username}")
	private String fromMail;

	@Value("${mail.password}")
	private String passMail;

	public void sendEmailSmtp(String toMail, String messageHtml, String titulo) {

		try {
			openConnection();
			MimeMessage message = new MimeMessage(transport.getSession());
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromMail, "dimexpruebas@gmail.com");
			helper.setTo(toMail);
			helper.setSubject(titulo);
			helper.setText(messageHtml, true);
			transport.sendMessage(message);
			log.info("Mensaje enviado");

		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

	public void openConnection() {
		closeConnection();

		if (mail1 < 90) {
			mail1++;
		} else {
			mail1 = 0;
		}

		resetConnection();

		if (mail1 == 1) {
			try {
				smtpConnectionPool = new SmtpConnectionPool(getSmtpConnection());
				transport = smtpConnectionPool.borrowObject();
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	public void resetConnection() {
		if (smtpConnectionPool != null && smtpConnectionPool.isClosed() && mail1 > 1) {
			mail1 = 1;
		}
		if (transport != null && !transport.isConnected() && mail1 > 1) {
			mail1 = 1;
		}
	}

	public void closeConnection() {
		if (mail1 == 90) {
			mail1++;
			smtpConnectionPool.close();
		}
	}

	public SmtpConnectionFactory getSmtpConnection() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "false");
		return SmtpConnectionFactoryBuilder.newSmtpBuilder().session(props).protocol("smtp")
				.host("smtp.gmail.com").port(587).username(fromMail).password(passMail).build();
	}
}
