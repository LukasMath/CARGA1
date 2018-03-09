package carga.esculta;

import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;
import carga.esculta.factory.EscultaFactory;
import carga.esculta.util.SistemaEsculta;

public class EscultaRecadastramento extends Esculta implements Runnable {
		
	
	
	public void run() {
		
		try {
			esculta(EscultaFactory.escultaDiretorio(SistemaEsculta.Recadastramento));
		} catch (ExcecaoPropertiesNaoEncontrado e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
