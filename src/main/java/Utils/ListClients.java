package Utils;

import model.entities.Client;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ListClients {
    public ListClients() throws Exception {
        Scanner tec = new Scanner(System.in);
        tec.useLocale(Locale.US);

        Boolean isRun = true;

        do {
            System.out.println(System.lineSeparator());
            System.out.println("\033[0;1m" + " - Selecione uma Listagem" + System.lineSeparator());
            System.out.print("1 - Listar Todos clientes\n" +
                    "2 - Buscar por CPF\n" +
                    "3 - Voltar\n");
            int opt = tec.nextInt();

            switch (opt) {
                case 1 -> {
                    findEverything();
                }
                case 2 -> {
                    System.out.print("Entre com o CPF do cliente");
                    String cpf = tec.next();
                    findCpf(cpf);
                }
                default -> {
                    System.out.println("Opção Invalida");
                    isRun = false;
                }
            }
        } while (isRun);
    }

    private void findEverything() {
        var item = new Client();
        List lista = item.findAll();

        for (Object i : lista) {
            System.out.println(i);
        }
    }

    private void findCpf(String cpf) throws Exception {
        var item = new Client();
        Client lista = item.findByCpf(cpf);
        System.out.println(lista);
    }


}
