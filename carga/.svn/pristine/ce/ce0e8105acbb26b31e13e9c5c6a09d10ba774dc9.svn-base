package carga.dao.controller;

import java.io.FileReader;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import carga.dao.factory.ConexaoFactory;
import carga.dao.util.ArquivoUtil;

public class ControllerDao implements IControllerDao {

	IFuncaoController iFuncaoController;

	private ControllerDao() {
		iFuncaoController = FuncaoController.getInstance();
	}

	private static ControllerDao instance;

	public static ControllerDao getInstance() {

		if (instance == null)
			instance = new ControllerDao();

		return instance;

	}

	@Override
	public void inserirDados(Path caminhoArquivoSaida, Path entrada) throws Exception {
		Logger msg = Logger.getLogger(Thread.currentThread().getName());
		Connection conexao = null;

		try {

			FileReader fileReader = new FileReader(caminhoArquivoSaida.toFile());
			String nomeTabelaAux = ArquivoUtil.nomeTabelaAux(caminhoArquivoSaida.getFileName().toString());
			
			conexao = ConexaoFactory.abrirConexao(nomeTabelaAux , ArquivoUtil.recuperarNomeAmbienteDiretorio(entrada));

			CopyManager cm = new CopyManager((BaseConnection) conexao);
			cm.copyIn("COPY " + nomeTabelaAux
					+ " FROM STDIN WITH DELIMITER ';'  NULL AS '#' ",
					fileReader);

			msg.log(Level.INFO, "Conexão com a base bem-sucessida! "
					+ caminhoArquivoSaida.getNameCount());

			conexao.close();
			fileReader.close();
			msg.log(Level.INFO, "Conexão com a base finalizada "
					+ caminhoArquivoSaida.getNameCount());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;

		}

	}

}
