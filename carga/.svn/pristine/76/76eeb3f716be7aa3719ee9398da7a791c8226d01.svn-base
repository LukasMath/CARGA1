package carga.dao.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoUtil {

	public static String removerDigitos(String nomeArquivo) {

		return nomeArquivo.replaceAll("[0-9]", "");
	}

	public static String removerExtensaoArquivo(String nomeArquivo) {

		return nomeArquivo.replaceFirst("[.][^.]+$", "");
	}

	public static String removerNomeSaida(String nomeArquivo) {

		return nomeArquivo.replaceAll("_SAIDA", "");
	}

	public static String removerTracoNomeArquivo(String nomeArquivo) {

		return nomeArquivo.replaceAll("-", "");
	}

	public static BufferedReader lerArquivo(File arquivo) throws IOException {

		return new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF8"));

	}
	
	public static String nomeTabelaAux (String nomeArquivoSaida){
		
		
		String nomeFormatado = "";
		
		nomeFormatado = ArquivoUtil.removerDigitos(nomeArquivoSaida);
		nomeFormatado = ArquivoUtil.removerNomeSaida(nomeFormatado);
		nomeFormatado = ArquivoUtil.removerExtensaoArquivo(nomeFormatado);
		nomeFormatado = ArquivoUtil.removerTracoNomeArquivo(nomeFormatado);
		
		if (Thread.currentThread().getName().equals(Sistema.SGD.getNome()))
			nomeFormatado = "sch_sad.aux_" + nomeFormatado.toLowerCase();
		else if (Thread.currentThread().getName().equals(Sistema.REDASTRAMENTO.getNome())){
			nomeFormatado = removerNomeSRS(nomeFormatado);
			nomeFormatado = "sch_recadastramento_anual.aux_" + nomeFormatado.toLowerCase();
			
		}
			
			
	
		
		return nomeFormatado;
		
		
	} 
	
	
	public static String removerNomeSRS (String nome){
		
		return nome.replaceAll("SRS_", "");
	}
	
	public static String recuperarNomeAmbienteDiretorio (Path caminhoPasta){
		return caminhoPasta.getFileName().toString();
	}	
}
