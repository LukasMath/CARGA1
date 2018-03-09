package carga.esculta.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	private static String FROM = "suporte.sad.pe.gov.br"; // Email do Gmail
															// usado
	private static String PASS = "sadsuporteusinf";

	/**
	 * Envia um email.
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 * @throws Exception
	 */
	public void enviarEmail(String[] to, String subject, String body) {
		try {
			sendFromGMail(FROM, PASS, to, subject, body);
		} catch (Exception e) {
			System.out.println("Erro ao enviar email de: " + FROM + "\nPara: "
					+ to);
		}

	}

	private static void sendFromGMail(String from, String pass, String[] to,
			String subject, String body) throws Exception {

		try {

			Properties props = System.getProperties();
			String host = "smtp.gmail.com";
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body, "utf-8", "html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

		} catch (AddressException ae) {
			ae.printStackTrace();
			throw new Exception(
					"Erro ao enviar as informações do chamado para o email indicado, verifique se o campo Email está preenchido corretamente!");
		} catch (MessagingException me) {
			me.printStackTrace();
			throw new Exception(
					"Erro ao enviar as informações do chamado para o email indicado, verifique se o campo Email está preenchido corretamente!");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"Erro ao enviar as informações do chamado para o email indicado, verifique se o campo Email está preenchido corretamente!");
		}
	}

}
