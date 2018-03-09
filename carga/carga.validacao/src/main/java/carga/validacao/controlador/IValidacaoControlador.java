package carga.validacao.controlador;

import java.io.IOException;
import java.nio.file.Path;

public interface IValidacaoControlador {
	
	void validarArquivos (Path entrada) throws IOException;
	
}
