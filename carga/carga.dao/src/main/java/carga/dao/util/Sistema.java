package carga.dao.util;

public enum Sistema {
	
	SGD("SGD"),
	REDASTRAMENTO("RECADASTRAMENTO");
	
	private String nome;
	
	private Sistema(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	
	
	
	
}
