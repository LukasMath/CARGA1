package carga.dao.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import carga.dao.exceptions.ExcecaoPropertiesNaoEncontrado;
import carga.dao.util.PropertiesUtil;

public class ConexaoFactory {

	public static Connection abrirConexao(String nomeSchema , String ambiente)
			throws ExcecaoPropertiesNaoEncontrado, ClassNotFoundException,
			SQLException {

		Properties pt = PropertiesUtil.getInstance().getPropertiesDao(nomeSchema, ambiente);

		String connectionURL = pt.getProperty("jdbc.connectionURL");
		Class.forName(pt.getProperty("jdbc.driverClass"));

		return DriverManager.getConnection(connectionURL, pt);

	}
	
	
	public static Connection abrirConexao(String ambiente)
			throws ExcecaoPropertiesNaoEncontrado, ClassNotFoundException,
			SQLException {

		Properties pt = PropertiesUtil.getInstance().getPropertiesDao(ambiente);

		String connectionURL = pt.getProperty("jdbc.connectionURL");
		Class.forName(pt.getProperty("jdbc.driverClass"));

		return DriverManager.getConnection(connectionURL, pt);

	}
	
	
	
	

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, ExcecaoPropertiesNaoEncontrado, IOException {
		System.out.println(abrirConexao("sch_sgd.sgd_avaliacao_servidor_participante", "pro").getCatalog());
	}

}
