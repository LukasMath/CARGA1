package carga.dao.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;

public class PropertiesUtil {

	private static PropertiesUtil instance;

	private static final String NM_PROPERTIES_SGD = "dao_sgd.properties";
	private static final String NM_PROPERTIES_REC = "dao_rec.properties";
	private static final String NM_PROPERTIES = "dao.properties";

	private PropertiesUtil() {
	}

	public static PropertiesUtil getInstance() {

		if (instance == null) {
			instance = new PropertiesUtil();
		}

		return instance;
	}

	public Properties getPropertiesDao(String nomeSchema, String ambiente)
			throws ExcecaoPropertiesNaoEncontrado {

		Logger log = Logger.getLogger(Thread.currentThread().getName());
		
		String nomePropertie = nomeProperties(ambiente, nomeSchema, log);

		return recuperarProperties(nomePropertie, log);
	}
	
	
	public Properties getPropertiesDao(String ambiente)
			throws ExcecaoPropertiesNaoEncontrado {

		Logger log = Logger.getLogger(Thread.currentThread().getName());
		
		String nomePropertiesSchema = nomeProperties(ambiente, log);

		return recuperarProperties(nomePropertiesSchema, log);
	}
	
	private String nomeProperties (String ambiente, String nomeSchema, Logger log ){
		
		log.log(Level.CONFIG,
				"Lendo propertie de configuração do banco de dados");

		String nmProperties;

		if (ambiente.equals("pro")) {
			if (nomeSchema.contains("sch_sgd"))
				nmProperties = NM_PROPERTIES_SGD;
			else if (nomeSchema.contains("sch_recadastramento_anual"))
				nmProperties = NM_PROPERTIES_REC;
			else
				nmProperties = NM_PROPERTIES;
		} else
			nmProperties = NM_PROPERTIES;

		return nmProperties;
		
	}
	
	
	private String nomeProperties (String ambiente, Logger log ){
		
		log.log(Level.CONFIG,
				"Lendo propertie de configuração do banco de dados");

		String nmProperties = "";

		if (ambiente.equals("pro")) {
			
		 String nomeSistema = 	Thread.currentThread().getName()
			.equals(Sistema.SGD.getNome()) ? Sistema.SGD.getNome() : Sistema.REDASTRAMENTO.getNome();
			
			if(nomeSistema.equals(Sistema.SGD.getNome()))
				nmProperties = NM_PROPERTIES_SGD;
			else
				nmProperties = NM_PROPERTIES_REC;
		
		} else
			nmProperties = NM_PROPERTIES;

		return nmProperties;
		
	}

	private Properties recuperarProperties(String nmProperties , Logger log) {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					nmProperties);
			InputStreamReader is = new InputStreamReader(input, "UTF8");
			prop.load(is);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		log.log(Level.CONFIG, "Propertie configurado com sucesso");

		return prop;

	}

}
