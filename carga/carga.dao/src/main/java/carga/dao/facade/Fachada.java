package carga.dao.facade;

import java.nio.file.Path;
import java.sql.SQLException;

import carga.dao.controller.ControllerDao;
import carga.dao.controller.FuncaoController;
import carga.dao.controller.IControllerDao;
import carga.dao.controller.IFuncaoController;
import carga.dao.exceptions.ExcecaoCriarTabela;
import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;

public class Fachada implements IFachada {
	
	
	private static Fachada instance;
	private IControllerDao iControllerDao;
	private IFuncaoController iFuncaoController;
	
	
	private Fachada() {
		iControllerDao = ControllerDao.getInstance();
		iFuncaoController = FuncaoController.getInstance();
	}
	
	
	public static Fachada getInstance (){
		
		if (instance == null)
			instance = new Fachada();
		
		
		return instance;
		
	}
	
	
	
	@Override
	public void inserirDados(Path caminhoArquivoSaida, Path entrada) throws Exception {
		iControllerDao.inserirDados(caminhoArquivoSaida, entrada);
	}


	@Override
	public void criarTabelasFuncao(Path caminhoArquivoSaida) throws SQLException, ClassNotFoundException, ExcecaoPropertiesNaoEncontrado, ExcecaoCriarTabela {
		iFuncaoController.criarTabelasFuncao(caminhoArquivoSaida);
		
	}
	
	
	
}
