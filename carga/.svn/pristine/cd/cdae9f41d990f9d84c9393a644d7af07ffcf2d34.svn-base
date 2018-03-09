package carga.validacao.fachada;

import java.io.IOException;
import java.nio.file.Path;

import carga.validacao.controlador.ValidacaoControlador;

public class Fachada implements IFachada {

	private static Fachada instance;
	private ValidacaoControlador validacaoControlador;
	

	private Fachada() {
		
		validacaoControlador = ValidacaoControlador.getInstance();
		
	}

	public static Fachada getInstance() {

		if (instance == null)
			instance = new Fachada();

		return instance;
	}

	public  void  validarArquivos(Path entrada) throws IOException {
		validacaoControlador.validarArquivos(entrada);
	}

}
