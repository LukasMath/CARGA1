package carga.validacao.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import carga.dao.facade.Fachada;
import carga.dao.facade.IFachada;
import carga.dao.util.ArquivoUtil;
import carga.validacao.util.EmailLog;
import carga.validacao.util.EmailUtil;
import carga.validacao.util.StringUtil;

public class ValidacaoControlador {

	private static final String DELIMITADOR = ";";
	private static String OS = System.getProperty("os.name").toLowerCase();
	private IFachada fachadaDAO;
	private EmailUtil emailUtil;
	private static ValidacaoControlador intance;

	private ValidacaoControlador() {

		fachadaDAO = Fachada.getInstance();
	}

	public static ValidacaoControlador getInstance() {

		if (intance == null)
			intance = new ValidacaoControlador();

		return intance;

	}

	public File getGerarArquivoEscrita(File arquivoLeitura, Path entrada)
			throws IOException {

		Logger logger = Logger.getLogger(Thread.currentThread().getName());

		Path arquivoSaida = Paths
				.get(entrada.getParent() + "/../arquivosSaida");

		try {

			if (!Files.exists(arquivoSaida)) {
				Files.createDirectories(arquivoSaida);
			}
		} catch (Exception e) {
			System.out.println(e.getCause());
		}

		File arquivo = null;

		Path saidaArquivo = Paths.get(arquivoSaida + "/"
				+ arquivoLeitura.getName().replaceFirst("[.][^.]+$", "")
				+ "_SAIDA" + ".txt");

		if (!Files.exists(saidaArquivo)) {
			arquivo = Files.createFile(saidaArquivo).toFile();
		} else {
			arquivo = saidaArquivo.toFile();
		}

		logger.log(Level.INFO,
				"Gerando o arquivo de saida: " + arquivo.getName());

		return arquivo;

	}

	public BufferedWriter gerarEscrita(File arquivo)
			throws FileNotFoundException {
		OutputStreamWriter writer = null;

		writer = new OutputStreamWriter(new FileOutputStream(arquivo),
				StandardCharsets.UTF_8);
		return new BufferedWriter(writer);
	}

	public void validarArquivos(Path entrada) throws IOException {
		BufferedWriter escrita = null;
		BufferedReader leitura = null;
		File arquivoSaida = null;		

		Logger logger = Logger.getLogger(Thread.currentThread().getName());
		logger.log(Level.INFO,
				"Lendo todos arquivos da pasta: " + entrada.toAbsolutePath());

		List<File> arquivosLeitura = getTodosArquivosPasta(entrada);
		String sistemaOperacional = "";

		String nomeTabela = null;		

		for (int i = 0; i < arquivosLeitura.size(); i++) {

			File arquivoLeitura = arquivosLeitura.get(i);

			logger.log(Level.INFO, "O arquivo que está sendo validado é: "
					+ arquivoLeitura.getName());

			try {
				synchronized (this) {
					nomeTabela = ArquivoUtil.nomeTabelaAux(arquivoLeitura
							.getName().toString());					

					leitura = getArquivoLeitura(arquivoLeitura);
					arquivoSaida = getGerarArquivoEscrita(arquivoLeitura,
							entrada);
					escrita = gerarEscrita(arquivoSaida);

					logger.log(Level.INFO,
							" Começando a validação das linhas do arquivo: "
									+ arquivoLeitura.getName());
					EmailLog.adicionarMsg("Validar linhas do arquivo");

					String linha = "";
					while ((linha = leitura.readLine()) != null) {

						StringTokenizer leituraLinha = new StringTokenizer(
								linha, DELIMITADOR, true);

						StringBuffer linhaValidada = new StringBuffer();

						while (existeProximoElemento(leituraLinha)) {

							String campo = proximoCampo(leituraLinha);

							String campoFormatado = "";
							campoFormatado = removerAcentos(campo);
							campoFormatado = removerEspaco(campoFormatado);
							campoFormatado = removerEspacosDuplicados(campoFormatado);
							campoFormatado = removerQuebraLinha(campoFormatado);

							linhaValidada.append(campoFormatado);

						}

						if (isWindows())
							sistemaOperacional = "\r\n";
						else
							sistemaOperacional = "\n";

						escrita.write(linhaValidada + sistemaOperacional);
						escrita.flush();

					}

					logger.log(Level.INFO, " Concluida validação do arquivo:  "
							+ arquivoLeitura.getName());
					EmailLog.adicionarMsg("Inserir Dados.");
					
					fachadaDAO.inserirDados(Paths.get(arquivoSaida.getPath()),
							entrada);
				}

				EmailLog.adicionarMsg("Concluído.");
				EmailLog.adicionarNomeTabela(nomeTabela);
				//aqui leandro
				EmailLog.limparMensagem();				
			} catch (Exception e) {

				EmailLog.adicionarMsg("Detalhes do Erro:\n" + e.getMessage());
				EmailLog.adicionarNomeTabela(nomeTabela);
				EmailLog.limparMensagem();

			} finally {

				escrita.close();
				leitura.close();
			}
		}

	}

	private String nomeTabelaAux(File arquivoSaida) {

		String nomeTabelaAux = "";

		if (arquivoSaida == null)
			return null;

		nomeTabelaAux = ArquivoUtil.nomeTabelaAux(arquivoSaida.getName());

		return nomeTabelaAux;
	}

	private String removerQuebraLinha(String campo) {

		return campo.contains("\\N") ? campo.replace("\\N", "#") : campo;
	}

	private Boolean existeProximoElemento(StringTokenizer leituraLinha) {

		return leituraLinha.hasMoreElements();
	}

	private String proximoCampo(StringTokenizer proximoElemento) {

		return proximoElemento.nextElement().toString();

	}

	private String removerAcentos(String campo) {

		String novo = new String();

		for (int i = 0; i < campo.length(); i++) {

			char letra = campo.charAt(i);

			if (letra == '¢') {
				letra = 'C';
			}

			novo += letra;

		}

		return StringUtil.removerAcentos(novo);
	}

	private String removerEspaco(String campo) throws IOException {
		return campo.trim();

	}

	private String removerEspacosDuplicados(String campo) {

		return StringUtil.removeSpacosDuplicados(campo);
	}

	private List<File> getTodosArquivosPasta(Path caminho) throws IOException {

		List<File> filesInFolder = Files.walk(caminho)
				.filter(Files::isRegularFile).map(Path::toFile)
				.collect(Collectors.toList());

		return filesInFolder;
	}

	private BufferedReader getArquivoLeitura(File arquivoLeitura)
			throws IOException {

		BufferedReader br = null;
		InputStreamReader isr = null;
		FileInputStream fi = null;
		fi = new FileInputStream(arquivoLeitura);
		isr = new InputStreamReader(fi, "ISO-8859-1");
		br = new BufferedReader(isr);

		return br;
	}

	private Boolean isWindows() {

		return (OS.indexOf("win") >= 0);
	}

}
