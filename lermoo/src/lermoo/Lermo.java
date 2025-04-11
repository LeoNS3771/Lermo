package lermoo;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lermo {
	
	public boolean verificarPos(char[] p, char[] t) {
		Map<Character, Integer> dicPalavra = new HashMap<>();
		Map<Character, Integer> dicTentativa = new HashMap<>();	
		Map<Character, Integer> repetidos = new HashMap<>();
		
		Palavra objetivo = new Palavra(p, dicPalavra);
		Palavra tentativa = new Palavra(t, dicTentativa);
		
		objetivo.criarMap();
		tentativa.criarMap();
		int acertos = 0;
		for(int i = 0; i<objetivo.caracteres.length; i++) {
			char cO = objetivo.caracteres[i];
			char cT = tentativa.caracteres[i];
			
			int diferenca = objetivo.mapa.getOrDefault(cT, 0) - tentativa.mapa.getOrDefault(cT, 0);
			int posicionados = objetivo.mapa.get(cO) - repetidos.getOrDefault(cT, 0);
			
			if(cO == cT) {
				System.out.print("✅");
				repetidos.put(cO, repetidos.getOrDefault(cO, 0) + 1);
				acertos++;
			}
			
			else if(diferenca >= 0)
				System.out.print("🔄");
			
			else if(objetivo.mapa.getOrDefault(cT, 0) != 0 && diferenca < 0) {
				
				if(posicionados > 0) {
					System.out.print("🔄");
				}
				else
					System.out.print("❌");
			}
			else
				System.out.print("❌");
		}
		System.out.println();
		
		if(acertos == 5) 
			return true;
		else
			return false;
	}
	
	
	public boolean validarPalavra(String palavra) {
		
		if(palavra.length()!=5)
			return false;
		
		return true;
	}
	
	
	
	
	
}
