package br.com.miniware.alpha;

public class Alpha {

	public static String resolver(String string) {
		String[] termos = string.split("=");
		if (termos[0].equals("x")) {
			if (termos[1].contains("+")) {
				String[] parcelas = termos[1].split("\\+");
				double s = 0;
				for (int i = 0; i < parcelas.length; i++) {
					s += Double.parseDouble(parcelas[i]);

				}
				return termos[0] + "=" + s;
			} else {
				double v = Double.parseDouble(termos[1]);
				return "Resposta final";
			}
		}
		return null;
	}
}
