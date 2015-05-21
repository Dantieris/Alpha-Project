package br.com.miniware.alpha;

import java.util.Arrays;

public class Resolver {

	private static String resultado = "";
	private static String[] termos = null;

	public static String resolver(String expressao) {
		// Separa a equa��o em dois termos caso for nulo.
		termos = expressao.split("=");

		// Verifica se na primeira parte da equa��o est� um x isolado.
		if (termos[0].equals("x")) {
			// Verifica se a segunda parte cont�m uma soma.
			if (termos[1].contains("+")) {
				// Realiza a soma.
				String soma = soma(termos[1]);
				
				resultado = termos[0] + "=" + soma;
				
				return resultado;
			} else {
				// Retorna o valor da segunda parte da equa��o.
				double v = Double.parseDouble(termos[1]);

				resultado = termos[0] + "=" + v;

				return resultado;
			}
		} else {
			// Verifica se a segunda parte cont�m uma soma.
			if (termos[1].contains("+")) {
				// Realizar a soma.
				String soma = soma(termos[1]);
				
				// Colocando o resultado na segunda parte da equa��o.
				termos[1] = soma;
				resultado += termos[0] + "=" + soma + ";";
			}

			// Verifica se as duas partes podem ser subtra�das por um numero comum.
			if (termos[0].contains("+")) {
				String termoZero[] = termos[0].split("\\+");

				double valor = Double.parseDouble(termoZero[1]);

				termos[0] += "-" + valor;
				termos[1] += "-" + valor;

				resultado = termos[0] + "=" + termos[1] + ";";
			}

			// Realiza subtra��o nos dois termos
			if (termos[0].contains("-") && termos[1].contains("-")) {
				termos[0] = subtracao(termos[0]);
				termos[1] = subtracao(termos[1]);
				
				resultado += termos[0] + "=" + termos[1] + ";";
			}

			// Verifica se as duas partes da equa��o s�o divis�veis por um valor.
			if (termos[0].contains("x")) {
				String termoZero = termos[0].replaceAll("x", "");

				double valor = Double.parseDouble(termoZero);

				termos[0] += "/" + valor;
				termos[1] += "/" + valor;

				resultado += termos[0] + "=" + termos[1] + ";";
			}

			//Realiza divis�o dos dois termos.
			if (termos[1].contains("/")) {
				divisaoDoisTermos();
			}
			
			
			if (termos[0].contains("/") && termos[0].contains("x")) {
				divisao();
			}
		}
		return resultado;
	}

	/**
	 * 
	 */
	private static void divisao() {
		String[] parcelas = termos[0].split("/");
		double s = 0;

		String termoZero = parcelas[0].replaceAll("x", "");

		s = Double.parseDouble(termoZero)
				/ Double.parseDouble(parcelas[1]);

		// Testando se o resultado � neutro, igual a 1.
		if (s == 1.0) {
			termos[0] = "x";
		} else {
			termos[0] = String.valueOf(s);
		}

		// Colocando o resultado na segunda parte da equa��o.
		resultado += termos[0] + "=" + termos[1];
	}

	/**
	 * 
	 * 
	 */
	private static void divisaoDoisTermos() {
		String[] parcelas = termos[1].split("/");
		double s = 0;

		if (Double.parseDouble(parcelas[0])
				% Double.parseDouble(parcelas[1]) == 0) {
			//Realiza a divis�o da parcela [0] dividendo pela parcelas[1] divisor.
			s = Double.parseDouble(parcelas[0])
					/ Double.parseDouble(parcelas[1]);

			// Colocando o resultado na segunda parte da equa��o.
			termos[1] = String.valueOf(s);
			resultado += termos[0] + "=" + s + ";";
		}
		//Divis�o com x na parcela[].
		if (termos[0].contains("/") && termos[0].contains("x")) {
			parcelas = termos[0].split("/");
			s = 0;

			String termoZero = parcelas[0].replaceAll("x", "");

			s = Double.parseDouble(termoZero)
					/ Double.parseDouble(parcelas[1]);

			// Testando se o resultado � neutro, igual a 1.
			if (s == 1.0) {
				termos[0] = "x";
			} else {
				termos[0] = String.valueOf(s);
			}

			// Colocando o resultado na segunda parte da equa��o.
			resultado += termos[0] + "=" + termos[1];
		}
	}

	/**
	 * 
	 */
	private static String subtracao(String termo) {
		if (termo.contains("x")) {
			String[] parcelasUm = termo.split("\\-");
			String[] parcelasDois = parcelasUm[0].split("\\+");
			double s = Double.parseDouble(parcelasUm[1]);

			s -= Double.parseDouble(parcelasDois[1]);
			
			if (s == 0.0) {
				return parcelasDois[0];
			} else {
				return String.valueOf(s);
			}
		} else {
			String[] parcelas = termo.split("\\-");
			
			double s = 0;
			
			s = Double.parseDouble(parcelas[0]);

			s -= Double.parseDouble(parcelas[1]);
			
			return String.valueOf(s);
		}		
	}

	/**
	 * return 
	 */
	private static String soma(String termo) {
		String[] parcelas = termo.split("\\+");
		double s = 0;
		for (int i = 0; i < parcelas.length; i++) {
			s += Double.parseDouble(parcelas[i]);
		}
		return String.valueOf(s);
	}

}
