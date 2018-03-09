package carga.validacao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carga.validacao.util.EmailUtil;

public class EmailLog {

	private static EmailUtil emailUtil;
	private static Map<String, List<String>> log = new HashMap<String, List<String>>();

	private static List<String> mensagens = new ArrayList<String>();

	public static void adicionarMsg(String... mensagem) {

		for (int i = 0; i < mensagem.length; i++) {
			mensagens.add(mensagem[i]);
		}

	}

	public static void limparMensagem() {
		mensagens = new ArrayList<String>();
	}

	public static void adicionarNomeTabela(String chave) {
		log.put(chave, mensagens);
	}

	public static Map<String, List<String>> getLog() {
		return log;
	}

	public static void setLog(Map<String, List<String>> log) {
		EmailLog.log = log;
	}

	public static void limparTodosLog() {
		log = new HashMap<String, List<String>>();
	}

	public static void limparLog(String chave) {
		log.put(chave, new ArrayList<String>());
	}

	public static void enviarEmailDeTodosLogs() {
		emailUtil = new EmailUtil();
		emailUtil.enviarEmail(log);
		limparTodosLog();

	}

}
