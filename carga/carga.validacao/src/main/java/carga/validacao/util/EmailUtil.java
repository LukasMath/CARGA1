package carga.validacao.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;
import carga.dao.util.PropertiesUtil;
import carga.validacao.factory.FreemarkerEmailFactory;

public class EmailUtil {

	// ATRIBUTOS
	private String from;
	private String password;
	private String assunto;
	private String[] to;
	private String host;
	private String body;
	private Properties prop;

	// GETTERS e SETTERS

	/**
	 * Construtor padrão
	 */
	public EmailUtil() {
		try {
			prop = getPropertiesEmail("email.properties");
		} catch (ExcecaoPropertiesNaoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinatarios = prop.getProperty("email.destinatario1");
		 
				
		to = prop.getProperty("email.destinatario").split(";");
		from = prop.getProperty("mail.smtp.from");
		password = prop.getProperty("mail.smtp.password");
		assunto = prop.getProperty("email.assunto");
		host = prop.getProperty("mail.smtp.host");
	}

	/**
	 * Envia um email.
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 * @throws Exception
	 */
	public void enviarEmail(Map<String, List<String>> log) {
		try {
			FreemarkerEmailFactory fmef;
			assunto += getDataAtual();			

			try {
				fmef = FreemarkerEmailFactory.getInstance();
				body = fmef.pegarHTMLFreeMarker(log);
			} catch (Exception e) {
				throw new Exception(e.getCause());
			}

			sendFromGMail();
			System.out.println("Email enviado para: " + to[0]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private String getDataAtual() {
		String dataAtual = null;

		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		dataAtual = sdf.format(d);
		return dataAtual;
	}

	public void sendFromGMail() throws Exception {

		try {

			Session session = Session.getDefaultInstance(prop);
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

			message.setSubject(assunto); 
			// message.setText(body, "utf-8", "html");
			message.setContent(body, "text/html; charset=utf-8");

			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
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

	// public static void main(String args[]) {
	// Properties prop = null;
	// try {
	// prop = getPropertiesEmail("email.properties");
	// } catch (ExcecaoPropertiesNaoEncontrado e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // get the property value and print it out
	// System.out.println(prop.getProperty("email.assunto"));
	//
	// }

	public Properties getPropertiesEmail(String nomeProperties)
			throws ExcecaoPropertiesNaoEncontrado {

		Logger log = Logger.getLogger(Thread.currentThread().getName());

		log.log(Level.CONFIG, "Lendo propertie do email");

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					nomeProperties);
			InputStreamReader is = new InputStreamReader(input, "UTF8");
			prop.load(is);
			log.log(Level.CONFIG, "Propertie configurado com sucesso");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return prop;
	}
}
