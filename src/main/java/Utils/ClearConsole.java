package Utils;

import java.io.IOException;
import java.util.Scanner;

public class ClearConsole { // Encontrado em: https://dicasdejava.com.br/java-como-limpar-a-tela-do-console/
    public void cleanConsole() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Clear");

        //Limpa a tela no windows, no linux e no MacOS
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");

    }
}
