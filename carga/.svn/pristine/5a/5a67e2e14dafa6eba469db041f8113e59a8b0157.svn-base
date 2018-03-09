package carga.validacao.util;

import java.math.BigInteger;
import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




/**
 * Classe utilitÃ¡ria para manipulaÃ§Ã£o de Strings.
 * 
 */
public final class StringUtil {

    
    public static String removerAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }    
	
	/**
	 * Verifica se ums String eh nula ou vazia
	 * 
	 * @param texto
	 * @return boolean
	 */
	public static boolean isEmpty(String texto) {
		if (texto == null || texto.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
	

	/**
	 * Obtem o CNPJ do Fornecedor e retorna o Cnpj formatado com a mï¿½scara
	 * 99.999.999/9999-99
	 * @param cnpj
	 * @return o CNPJ formatado
	 */
	public static String obterCNPJFormatado(String cnpj){
		char[] array = cnpj.toCharArray();
		String cnpjFormatado = "";

		try{
			cnpjFormatado = String.valueOf(array[0]) 
			+ String.valueOf(array[1]) + "." 
			+ String.valueOf(array[2])
			+ String.valueOf(array[3])
			+ String.valueOf(array[4]) + "."
			+ String.valueOf(array[5])
			+ String.valueOf(array[6])
			+ String.valueOf(array[7]) + "/"
			+ String.valueOf(array[8])
			+ String.valueOf(array[9])
			+ String.valueOf(array[10])
			+ String.valueOf(array[11]) + "-"
			+ String.valueOf(array[12])
			+ String.valueOf(array[13]);
		}catch (Exception e) {
			cnpjFormatado = cnpj;
		}

		return cnpjFormatado;
	}
	
	
	
	public final static String convertNormalCase(String text) {
		StringBuilder str = new StringBuilder();
		String[] textSplit = text.toLowerCase().trim().split("\\s+");
		for (String string : textSplit) {
			str.append(convertFirstLetterUpperCase(string)).append(" ");
		}
		return str.toString();
	}
	

	public final static String convertFirstLetterToLowerCase(String palavra) {
		char[] palavraArray = palavra.toCharArray();
		palavraArray[0] = Character.toLowerCase(palavraArray[0]);
		return String.valueOf(palavraArray);
	}
	
	
	public final static String convertFirstLetterUpperCase(String palavra) {
		char[] palavraArray = palavra.toCharArray();
		palavraArray[0] = Character.toLowerCase(palavraArray[0]);
		return String.valueOf(palavraArray);
	}	
	
	/**
	 * Retorna se a String informada possui letras e digitos.
	 * 
	 * @param string
	 *            a string
	 * @return <code>true</code> se a String informada possuir letras e
	 *         digitos, <code>false</code> caso contrï¿½rio.
	 */
	public static boolean possuiDigitosLetras(String string) {

		boolean possuiLetras = false;
		boolean possuiDigitos = false;

		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i))) {
				possuiDigitos = true;
			}
			if (Character.isLetter(string.charAt(i))) {
				possuiLetras = true;
			}
		}

		return (possuiLetras && possuiDigitos);
	}

	/**
	 * Retorna se a String informada possui apenas digitos.
	 * 
	 * @param string
	 *            a string
	 * @return <code>true</code> se a String informada possuir apenas digitos,
	 *         <code>false</code> caso contrï¿½rio.
	 */
	
		
	

	/**
	 * Retorna se a String informada possui letras.
	 * 
	 * @param string
	 *            a string a ser pesquisado.
	 * @return <code>true</code> se a String informada possuir letras,
	 *         <code>false</code> caso contrï¿½rio.
	 */
	public static boolean possuiLetras(String string) {

		boolean possuiLetras = false;

		for (int i = 0; i < string.length(); i++) {
			if (Character.isLetter(string.charAt(i))) {
				possuiLetras = true;
			}
		}

		return possuiLetras;
	}

	/**
	 * Completa a string informada com zeros a esquerda atÃ© que ela tenha o
	 * tamanho informado nos parametros.
	 * 
	 * @param string
	 *            a string
	 * @param tamanho
	 *            o tamanho limite da string
	 * @return a string informada com zeros a esquerda e do tamanho informado.
	 */
	public static String completarZerosEsquerda(String string, int tamanho) {
		return preencherEsquerda(string, tamanho, '0');
	}

	/**
	 * Verifica se uma string passada tem valor nulo ou esta em branco.
	 * 
	 * @param string
	 *            string a ser testada
	 * @return <code>true</code> caso a string passada seja nula ou esteja em
	 *         branco, <code>false</code> caso a string passada seja diferente
	 *         de nulo ou nao esteja em branco
	 */
	public static boolean ehBrancoOuNulo(String string) {
		return string == null || string.trim().equals("");
	}

	/**
	 * Retorna uma string contendo o valor inteiro informado e com o tamanho
	 * desejado com zeros a esquerda.
	 * 
	 * @param inteiro
	 *            um int contendo o nï¿½mero a ser convertido
	 * @param tamanho
	 *            o tamanho limite da string
	 * @return a string informada com zeros a esquerda e do tamanho informado.
	 */
	public static String completarZerosEsquerda(int inteiro, int tamanho) {
		return completarZerosEsquerda(String.valueOf(inteiro), tamanho);
	}

	/**
	 * Inverte o conteÃºdo da String informada. Se o valor for null, uma String
	 * vazia sera retornada.
	 * 
	 * @param valor
	 *            A String que deverï¿½ ser utilizada para inversÃ£o.
	 * @return Uma String com o conteÃºdo do parametro informado em ordem inversa
	 */
	public static String inverterString(String valor) {

		StringBuffer sb = new StringBuffer();

		if (valor != null) {
			for (int i = valor.length() - 1; i >= 0; i--) {
				sb.append(valor.charAt(i));
			}
		}

		return sb.toString();
	}

	/**
	 * Preenche a string com o filler a direita atÃ© que o seu tamanho seja o
	 * tamanho informado. Ex: preencherDireita("teste", 10, '#') = "teste#####"
	 * 
	 * @param s
	 *            A string original.
	 * @param tamanho
	 *            O tamanho que deve ter a string ao final do mï¿½todo.
	 * @param filler
	 *            O character que sera utilizado para preencher a String.
	 * @return A string informada com o filler a direita atÃ© que o seu tamanho
	 *         seja o tamanho informado.
	 */
	public static String preencherDireita(String s, int tamanho, char filler) {

		StringBuffer sb = new StringBuffer(s);

		for (int i = sb.length(); i < tamanho; i++) {
			sb.append(filler);
		}

		return sb.toString();
	}

	/**
	 * Preenche a string com o filler a esquerda atÃ© que o seu tamanho seja o
	 * tamanho informado. Ex: preencherEsquerda("teste", 10, '#') = "#####teste"
	 * 
	 * @param s
	 *            A string original.
	 * @param tamanho
	 *            O tamanho que deve ter a string ao final do mï¿½todo.
	 * @param filler
	 *            O character que sera utilizado para preencher a String.
	 * @return A string informada com o filler a esquerda atÃ© que o seu tamanho
	 *         seja o tamanho informado.
	 */
	public static String preencherEsquerda(String s, int tamanho, char filler) {

		int repeticoes = tamanho - s.length();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < repeticoes; i++) {
			sb.append(filler);
		}

		sb.append(s);

		return sb.toString();

	}

	/**
	 * Retorna se o email informado eh vï¿½lido.
	 * 
	 * @param email
	 *            uma String contendo o e-mail a ser validado.
	 * @return <code>true</code> se o email informado eh vï¿½lido,
	 *         <code>false</code> caso contrï¿½rio.
	 */
	public static boolean ehEmailValido(String email) {

		boolean ehEmailValido = false;

		Pattern padrao = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher pesquisa = padrao.matcher(email);

		if (pesquisa.matches()) {
			ehEmailValido = true;
		}

		return ehEmailValido;
	}

	/**
	 * Retorna uma String apenas com os digitos e as letras da String passada
	 * como argumento.
	 * 
	 * @param string
	 *            uma String contendo a string a ser "refinada"
	 * @return String contendo apenas digitos e letras
	 */
	public static String apenasDigitosLetras(String string) {

		StringBuffer letrasDigitos = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {
			char caracter = string.charAt(i);
			if (Character.isDigit(caracter) || Character.isLetter(caracter)) {
				letrasDigitos.append(caracter);
			}
		}

		return letrasDigitos.toString();
	}

	/**
	 * Retorna uma String apenas com os digitos da String passada como
	 * argumento.
	 * 
	 * @param string
	 *            uma String contendo a string a ser "refinada"
	 * @return String contendo apenas digitos
	 */
	public static String apenasDigitos(String string) {

		StringBuffer digitos = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {
			char caracter = string.charAt(i);
			if (Character.isDigit(caracter)) {
				digitos.append(caracter);
			}
		}

		return digitos.toString();
	}

	/**
	 * Remove os espaï¿½os duplicados de uma String.
	 * 
	 * @param string
	 *            uma String contendo a string a ser "refinada"
	 * @return String contendo apenas sem espaï¿½os duplicados
	 */
	public static String removeSpacosDuplicados(String string) {

		Pattern pattern = Pattern.compile("[\\s]+");

		Matcher matcher = pattern.matcher(string);

		return matcher.replaceAll(" ");
	}

	/**
	 * Retorna uma String apenas com as letras da String passada como argumento.
	 * 
	 * @param string
	 *            uma String contendo a string a ser "refinada"
	 * @return String contendo apenas letras
	 */
	public static String apenasLetras(String string) {

		StringBuffer letras = new StringBuffer();

		for (int i = 0; i < string.length(); i++) {
			char caracter = string.charAt(i);
			if (Character.isLetter(caracter)) {
				letras.append(caracter);
			}
		}

		return letras.toString();
	}

	/**
	 * Retorna <code>true</code> se uma String vem com o valor zero.
	 * 
	 * @param s
	 *            um tipo String contendo o valor a ser verificado
	 * @return <code>true</code> se uma String vem com o valor zero
	 */
	public static boolean preenchidaSoComZero(String s) {
		boolean retorno = true;
		BigInteger bigInteger = null;
		try {
			bigInteger = new BigInteger(s.trim());
		} catch (NumberFormatException e) {
			retorno = false;
		}
		if (bigInteger == null) {
			retorno = false;
		} else if (!bigInteger.equals(new BigInteger("0"))) {
			retorno = false;
		}
		return retorno;
	}
	
		public static String capitalize(final String str, final char... delimiters) {
		        final int delimLen = delimiters == null ? -1 : delimiters.length;
		        if ((str.isEmpty()) || delimLen == 0) {
		            return str;
		        }
		        final char[] buffer = str.toCharArray();
		        boolean capitalizeNext = true;
		        for (int i = 0; i < buffer.length; i++) {
		            final char ch = buffer[i];
		            if (isDelimiter(ch, delimiters)) {
		                capitalizeNext = true;
		            } else if (capitalizeNext) {
		                buffer[i] = Character.toTitleCase(ch);
		                capitalizeNext = false;
		            }
		        }
		        return new String(buffer);
		}
		
		public static String capitalizeFully(final String str) {
		       return capitalizeFully(str, null);
	    }
		
		private static String capitalizeFully(String str, final char... delimiters) {
			final int delimLen = delimiters == null ? -1 : delimiters.length;
			if ((str.isEmpty()) || delimLen == 0) {
			     return str;
			}
			str = str.toLowerCase();
			return capitalize(str, delimiters);
		}
		
		private static boolean isDelimiter(final char ch, final char[] delimiters) {
			  if (delimiters == null) {
			      return Character.isWhitespace(ch);
			 }
			     for (final char delimiter : delimiters) {
			     if (ch == delimiter) {
			         return true;
			     }
			 }
			 return false;
		}
		
		
}