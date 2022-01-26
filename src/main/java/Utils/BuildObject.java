package Utils;

import model.entities.Address;
import model.entities.Client;
import model.entities.Delivery;

import java.util.Locale;
import java.util.Scanner;

public class BuildObject {
    public BuildObject() throws Exception {
        Scanner inp = new Scanner(System.in);
        inp.useLocale(Locale.US);

        System.out.println("Entre com seu ENDEREÇO");

        System.out.print("Rua: ");
        String rua = inp.nextLine();

        System.out.print("Numero: ");
        int numero = inp.nextInt();
        clearBuffer(inp);

        System.out.print("Bairro: ");
        String bairro = inp.nextLine();

        System.out.print("Cidade: ");
        String cidade = inp.nextLine();

        System.out.print("CEP: ");
        String cep = inp.nextLine();

        System.out.print("UF: ");
        String uf = inp.nextLine();

        Address end = new Address(rua, numero, bairro, cidade, cep, uf);

        end = end.Register(end);
/////////////////////////////////////////////////////////////////////////
        if (end.getId() > 0) {
            System.out.print("Entre com seu Dados\n");

            System.out.print("Nome: ");
            String nome = inp.nextLine();
            System.out.print("CPF: ");
            String cpf = inp.nextLine();
            System.out.print("Produto: ");
            String produto = inp.nextLine();

            int client_address = end.getId();
            Client cli = new Client(nome, cpf, produto, client_address);
            cli = cli.Register(cli);

            if (cli.getId() > 0) {
                int delivery_client = cli.getId();

                Delivery del = new Delivery(
                        cli,
                        delivery_client
                );
                del.Register(del);
            }
        }
        System.out.println("Registro inserido com sucesso");
        new ClearConsole().cleanConsole();
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
