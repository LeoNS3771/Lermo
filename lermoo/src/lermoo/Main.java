package lermoo;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
    	List<String> listaPalavras = new ArrayList<>();
    	List<String> listaPalavrasVer = new ArrayList<>();
    	
        try {
        	URI uriC = URI.create("https://raw.githubusercontent.com/LeoNS3771/Lermo/refs/heads/main/palavrasLermo.txt");
            URL palavrasCorretas = uriC.toURL();
            
            URI uriV = URI.create("https://gist.githubusercontent.com/vncsmnl/25e7c165da276405af8ca4e1c8e17806/raw/aaeb75a75ff48ae8cd8888bae031dcb9884cddaa/wordlist");
            URL palavrasVerif = uriV.toURL();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(palavrasCorretas.openStream()));
            BufferedReader in2 = new BufferedReader(new InputStreamReader(palavrasVerif.openStream()));
            
            String linha;
            while((linha = in.readLine()) != null) {
            	listaPalavras.add(linha.trim());
            }
            while((linha = in2.readLine()) != null) {
            	listaPalavrasVer.add(linha.trim());
            }
            
            in.close();
            in2.close();
            
        } catch(Exception e){
        	System.out.println("Erro ao acessar a lista de palavras: " + e.getMessage());
        }
        
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String palavraEscolhida = listaPalavras.get(random.nextInt(listaPalavras.size()));
        
        // Definindo variaveis
        Lermo lermo = new Lermo();
        String entrada;
        int vidas = 6;
        
        // Explicacao do jogo
        System.out.println("---Lermo---\nAcerte a palavra de 5 letras usando as seguintes dicas:");
        System.out.println("✅ -> posicao correta\n🔄 -> letra correta, mas posicao errada\n❌ -> Letra errada");
        
        // Loop do jogo
        while (vidas > 0) {
            System.out.println("\nVidas restantes: " + vidas);
            
            // Entrada do jogador
            do {
            	System.out.print("-> ");
                entrada = scanner.nextLine().toUpperCase();
                
            } while (!lermo.validarPalavra(entrada, listaPalavrasVer));

            if (lermo.compararPalavras(palavraEscolhida, entrada)) {
                System.out.println("\nVoce acertou!\nCom " + vidas + " vidas restantes");
                scanner.close();
                return;
            }
            vidas--;
        }
        System.out.println("Voce perdeu\n" + "A palavra era: " + palavraEscolhida);
        
        scanner.close();
    }
}
