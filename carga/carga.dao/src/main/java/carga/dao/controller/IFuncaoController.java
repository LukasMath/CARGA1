package carga.dao.controller;

import java.nio.file.Path;
import java.sql.SQLException;

import carga.dao.exceptions.ExcecaoCriarTabela;
import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;

public interface IFuncaoController {
	
	public void criarTabelasFuncao(Path nomeTabelaAux) throws SQLException, ClassNotFoundException, ExcecaoPropertiesNaoEncontrado, ExcecaoCriarTabela;
}
