package carga.esculta.factory;

import java.util.Properties;

import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;
import carga.esculta.util.PropertiesUtilEsculta;
import carga.esculta.util.SistemaEsculta;

public class EscultaFactory {
	
	
	public  static String escultaDiretorio (SistemaEsculta sistemaEsculta) throws ExcecaoPropertiesNaoEncontrado{
		
		Properties properties =  PropertiesUtilEsculta.getInstance().getPropertiesEsculta();
		
		String diretorioEsculta = properties.getProperty("diretorio.esculta." + sistemaEsculta.getSistema());
		
		return diretorioEsculta;
		
	}
}
