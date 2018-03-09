package carga.esculta.util;

public enum SistemaEsculta {

	Recadastramento("recadastramento"), SGD("sgd");

	private String sistema;

	private SistemaEsculta(String sistema) {
		this.sistema = sistema;
	}

	public String getSistema() {
		return sistema;
	}

}
