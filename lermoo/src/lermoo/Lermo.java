package lermoo;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


public class Lermo {

	public boolean compararPalavras(String palavra, String entrada) {
		Map<Character, Integer> dicPalavra = new HashMap<>();
		Map<Character, Integer> dicTentativa = new HashMap<>();	
		
		char[] p = palavra.toCharArray();
		char[] t = entrada.toCharArray();
		
		Palavra objetivo = new Palavra(p, dicPalavra);
		Palavra tentativa = new Palavra(t, dicTentativa);
		
		objetivo.criarMap();
		tentativa.criarMap();
		int acertos = 0;
		String[] resultado = new String[5];
		Map<Character, Integer> letrasRestantes = new HashMap<>(objetivo.mapa);
		
		// Verificar acertos (✅) 
		for(int i = 0; i<5; i++) {
			char cO = objetivo.caracteres[i];  // cO = caracter do objetivo
			char cT = tentativa.caracteres[i]; // cT = caracter da tentativa
			
			if(cO == cT) {
				resultado[i] = "✅";
				acertos++;
				letrasRestantes.put(cT, letrasRestantes.get(cT) - 1); //Letra foi usada
			}
		}
		
		// Verificar acertos fora de posição (🔄) ou erros(❌)
		for(int i = 0; i<5; i++) {
			if(resultado[i] == null) {
				char cT = tentativa.caracteres[i];
				if(letrasRestantes.getOrDefault(cT, 0) > 0) {
					resultado[i] = "🔄";
					letrasRestantes.put(cT, letrasRestantes.get(cT) - 1);
				}
				else
					resultado[i] = "❌";
			}
		}
		
		// imprimindo resultado
		for(String r : resultado) {
			System.out.print(r);
		}
		System.out.println();
		
		if(acertos == 5) 
			return true;
		else
			return false;
	}
	
	
	public boolean validarPalavra(String palavra, List<String> listaPalavras) {
		if(palavra.length() == 5) {
			
			for(String s : listaPalavras) {
				if(palavra.equals(s))
					return true;
			}
		}
		
		return false;
	}
	
	
	
	
	
}
