package lermoo;
import java.util.Scanner;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        Random gerador = new Random();
        
        // Gerando palavra aleatoria do arquivo
        String palavra = null;
        int posAleatoria = gerador.nextInt(100) + 1; // de 1 a 100
        int i = 1;
        
        try (BufferedReader leitor = new BufferedReader(new FileReader("palavras.txt"))) {
            while ((palavra = leitor.readLine()) != null) {
                if (i == posAleatoria) {
                    break;
                }
                i++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        	
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
                entrada = scanner.nextLine().toLowerCase();
                
            } while (!lermo.validarPalavra(entrada));

            if (lermo.compararPalavras(palavra, entrada)) {
                System.out.println("\nVoce acertou!\nCom " + vidas + " vidas restantes");
                scanner.close();
                return;
            }
            vidas--;
        }
        System.out.println("Voce perdeu\n" + "A palavra era: " + palavra);
        
        scanner.close();
    }
}
