import Enums.Status;
import Utils.BuildObject;
import Utils.ListClients;
import Utils.ListDelivery;
import model.entities.Delivery;

import java.util.Locale;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        String espaco = System.lineSeparator();
        Boolean isRun = true;

        Scanner tec = new Scanner(System.in);
        tec.useLocale(Locale.US);

        System.out.println("\033[0;1m" + "---  Flash Point  ---" + espaco);

        do {
            System.out.println(" - Selecione uma função" + espaco);
            System.out.println("1 - Cadastrar entrega\n" +
                    "2 - Listar Entregas\n" +
                    "3 - Listar Clientes\n" +
                    "4 - Alterar Status\n" +
                    "5 - SAIR\n");
            int opt;
            opt = tec.nextInt();
            clearBuffer(tec);

            switch (opt) {
                case 1 -> {
                    new BuildObject();
                    isRun = true;
                }
                case 2 -> {
                    new ListDelivery();
                    System.out.println(espaco);
                    isRun = true;
                }
                case 3 -> {
                    new ListClients();
                    System.out.println(espaco);
                    isRun = true;
                }
                case 4 -> {
                    var del = new Delivery();
                    System.out.println("Entre com o id que deseja alterar");
                    int idDel = tec.nextInt();
                    System.out.println("Selecione o Numero do Status");
                    System.out.print("1 - Solicitado\n2 - Aguardando\n3 - Retirado\n4 - Entregue\n5 - Inativado\n");
                    int status = tec.nextInt();
                    Status ts = null;
                    switch (status) {
                        case 1 -> {
                            ts = Status.SOLICITADO;
                        }
                        case 2 -> {
                            ts = Status.AGUARDANDO;
                        }
                        case 3 -> {
                            ts = Status.RETIRADO;
                        }
                        case 4 -> {
                            ts = Status.ENTREGUE;
                        }
                        case 5 -> {
                            ts = Status.INATIVADO;
                        }
                        default -> System.out.println("Não valido");
                    }

                    del.UpdateStatus(idDel, String.valueOf(ts));
                    System.out.println(espaco);
                    isRun = true;
                }
                default -> {
                    System.out.println("Aplicação Finalizada");
                    isRun = false;
                }
            }

        } while (isRun);
        tec.close();

    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
