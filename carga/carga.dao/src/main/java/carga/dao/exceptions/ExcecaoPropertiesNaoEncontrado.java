package carga.dao.exceptions;

public class ExcecaoPropertiesNaoEncontrado  extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcecaoPropertiesNaoEncontrado() {
		super("Não foi encontrdo o dao.properties");
	}
	
	
}
