package carga.esculta;

import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;
import carga.esculta.factory.EscultaFactory;
import carga.esculta.util.SistemaEsculta;


public class EscultaSGD extends Esculta implements Runnable{

	
	@Override
	public void run() {
		try {
			esculta(EscultaFactory.escultaDiretorio(SistemaEsculta.SGD));
		} catch (ExcecaoPropertiesNaoEncontrado e) {
			e.printStackTrace();
		}
		
	}
	
}

