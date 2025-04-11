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

        Lermo lermo = new Lermo();
        char[] palavraC = palavra.toCharArray(); 

        String tentativa;
        int vidas = 6;
        while (vidas > 0) {
            System.out.print("Vidas restantes: " + vidas + "\n-> ");
            do {
                tentativa = scanner.nextLine();
            } while (!lermo.validarPalavra(tentativa));

            char[] caracteres = tentativa.toCharArray();
            if (lermo.verificarPos(palavraC, caracteres)) {
                System.out.println("Voce acertou!\n" + vidas + " pontos");
                scanner.close();
                return;
            }
            vidas--;
        }
        System.out.println("Voce perdeu\n" + "A palavra era: " + palavra);
        scanner.close();
    }
}
