package carga.dao.controller;

import java.nio.file.Path;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import carga.dao.exceptions.ExcecaoCriarTabela;
import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;
import carga.dao.factory.ConexaoFactory;
import carga.dao.util.ArquivoUtil;
import carga.dao.util.Funcao;
import carga.dao.util.Sistema;

public class FuncaoController implements IFuncaoController {

	private static FuncaoController instance;
	String nomeTabela = null;

	private FuncaoController() {

	}

	public static FuncaoController getInstance() {

		if (instance == null)
			instance = new FuncaoController();

		return instance;
	}

	public void criarTabelasFuncao(Path caminhoArquivoSaida)
			throws SQLException, ClassNotFoundException,
			ExcecaoPropertiesNaoEncontrado, ExcecaoCriarTabela {
		CallableStatement cs = null;
		try {
			nomeTabela = ArquivoUtil.nomeTabelaAux(caminhoArquivoSaida
					.toString()); // Deve tá errado.
			Connection conexao = ConexaoFactory.abrirConexao(ArquivoUtil
					.recuperarNomeAmbienteDiretorio(caminhoArquivoSaida));

			cs = conexao.prepareCall(Thread.currentThread().getName()
					.equals(Sistema.SGD.getNome()) ? Funcao.CRIAR_TABELA_SGD
					.getNomeFuncao() : Funcao.CRIAR_TABELA_RECADASTRAMENTO
					.getNomeFuncao());
			cs.executeUpdate();

		} catch (Exception e){
			throw new ExcecaoCriarTabela(e);
		}finally {
			cs.close();
		}

	}

}
