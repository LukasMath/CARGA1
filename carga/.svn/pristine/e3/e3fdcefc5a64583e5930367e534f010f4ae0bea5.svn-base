package carga.esculta.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;

public class PropertiesUtilEsculta {

	private static PropertiesUtilEsculta instance;

	private static final String NM_PROPERTIES = "esculta.properties";

	private PropertiesUtilEsculta() {
	}

	public static PropertiesUtilEsculta getInstance() {

		if (instance == null) {
			instance = new PropertiesUtilEsculta();
		}

		return instance;
	}

	public Properties getPropertiesEsculta() throws ExcecaoPropertiesNaoEncontrado {

		Logger log = Logger.getLogger(Thread.currentThread().getName());

		log.log(Level.CONFIG,"Lendo propertie de configuração do monitoramento dos arquivos");

		Properties prop = new Properties();
		FileInputStream input = null;

		try {

			input = new FileInputStream(new File(PropertiesUtilEsculta.class
					.getClassLoader().getResource(NM_PROPERTIES).getPath()));

			log.log(Level.CONFIG, "Propertie configurado com sucesso");

			prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
		

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

		return prop;
	}

}
