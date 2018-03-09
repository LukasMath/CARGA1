package carga.dao.facade;

import java.nio.file.Path;
import java.sql.SQLException;

import carga.dao.exceptions.ExcecaoCriarTabela;
import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;

public interface IFachada {
	
	void  inserirDados (Path caminhoArquivoSaida, Path entrada) throws Exception;
	void criarTabelasFuncao(Path caminhoArquivoSaida) throws SQLException, ClassNotFoundException, ExcecaoPropertiesNaoEncontrado, ExcecaoCriarTabela;
	
}
