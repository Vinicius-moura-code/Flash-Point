package model.entities;

import model.dao.ClientDAO;
import model.dao.FactoryDAO;
import model.entities.interfaces.IClient;
import services.database.ConnectionJDBC;

import java.util.ArrayList;
import java.util.List;

public class Client implements IClient {
    private int id;
    private String nome ;
    private String cpf;
    private String produto ;
    private Address endereco;
    private int client_address;

    public Client(){}

    public Client( String nome, String cpf, String produto, int client_address) {
        this.nome = nome;
        this.cpf = cpf;
        this.produto = produto;
        this.client_address = client_address;
    }

    public Client(String nome, String cpf, String produto, Address endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.produto = produto;
        this.endereco = endereco;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public int getClient_address() {
        return client_address;
    }

    public void setClient_address(int client_address) {
        this.client_address = client_address;
    }


    public Client Register (Client cliente) throws Exception {

        ClientDAO cd = FactoryDAO.createClient();

        try {
            cliente = cd.register(cliente);
        }catch (Exception ex){
            System.out.println("Não foi possivel incluir o registro.");
        }
        return cliente;
    }

    public List<Client> findAll(){
        ClientDAO dao = FactoryDAO.createClient();
        ArrayList listResult = new ArrayList<>();

        try{
            listResult = dao.findAll();
        }catch (Exception e){

        }
        return listResult;
    }

    public Client findByCpf(String cpf) throws Exception {
        ClientDAO dao = FactoryDAO.createClient();
        Client cli = null;
        try{
            cli = (Client) dao.findByCpf(cpf);
        }catch (Exception e){

        };
        return cli;
    }
    public String toString() {
        return "Cliente: " + nome + " | Cpf: "+ cpf + " | Endereço: " + endereco;
    }

}
