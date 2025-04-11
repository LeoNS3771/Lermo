package lermoo;
import java.util.Map;
import java.util.HashMap;

public class Palavra {
	char[] caracteres;
	Map<Character, Integer> mapa = new HashMap<>();
	
	Palavra(char[] word, Map<Character, Integer> dic){
		this.caracteres = word;
		this.mapa = dic;
	}
	
	public void criarMap() {
		for(char c: this.caracteres) {
			this.mapa.put(c, this.mapa.getOrDefault(c,0) + 1);  // adiciona 1 no caracter c, caso nao exista inicia com 0												      
		}
	}
}
