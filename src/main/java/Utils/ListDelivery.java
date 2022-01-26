package Utils;

import Enums.Status;
import model.entities.Delivery;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ListDelivery {
    public ListDelivery() throws Exception {
        Scanner tec = new Scanner(System.in);
        tec.useLocale(Locale.US);

        Boolean isRun = true;

        do {
            System.out.println(System.lineSeparator());
            System.out.println("\033[0;1m" + " - Selecione uma Listagem" + System.lineSeparator());
            System.out.print("1 - Listar Todas Entregas\n" +
                    "2 - Buscar Entrega\n" +
                    "3 - Listar Entregas Por status\n" +
                    "4 - DELETAR\n" +
                    "5 - SAIR\n");
            int opt = tec.nextInt();

            switch (opt) {
                case 1 -> {
                    findEverything();
                }
                case 2 -> {
                    System.out.println("Entre com o ID da entrega");
                    int id = tec.nextInt();
                    findId(id);
                }
                case 3 -> {
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
                    findStatus(String.valueOf(ts));
                }
                case 4 -> {
                    System.out.println("Entre com o ID da entrega a ser deletada");
                    int id = tec.nextInt();
                    Delete(id);
                }
                default -> {
                    isRun = false;
                }
            }
        } while (isRun);
    }

    private void findEverything() throws Exception {
        var item = new Delivery();
        List lista = item.findAll();

        for (Object i : lista) {
            System.out.println(i);
        }
    }

    private void findId(int id) throws Exception {
        var item = new Delivery();
        Delivery lista = item.findById(id);
        System.out.println(lista);
    }

    private void findStatus(String status) throws Exception {
        var item = new Delivery();
        List lista = item.findStatus(status);

        for (Object i : lista) {
            System.out.println(i);
        }
    }

    private void Delete(int id ){
        var item = new Delivery();
        item.DeleteDelivey(id);
    }
}
