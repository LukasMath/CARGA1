package carga.dao.util;

public enum Funcao {
	
	CRIAR_TABELA_SGD("{call sch_sad.fn_cria_tabela()}"),
	CARGA_SGD(" { call sch_sad.fn_carga(?, ? , ?)}"),
	CRIAR_TABELA_RECADASTRAMENTO("{call sch_recadastramento_anual.fn_cria_tabela()}");
	
	private String nomeFuncao;
	
	private Funcao(String nomeFuncao) {
		this.nomeFuncao = nomeFuncao;
	}

	public String getNomeFuncao() {
		return nomeFuncao;
	}
	
	
	
	
	
	
}
