package model.entities;

import model.dao.AddressDAO;
import model.dao.DeliveryDAO;
import model.dao.FactoryDAO;
import model.entities.interfaces.IAddress;


public class Address implements IAddress {
    private int id;
    private String Rua ;
    private int Numero;
    private String Bairro;
    private String Cidade;
    private String CEP;
    private String UF;


    public Address(){}

    public Address(String rua, int numero, String bairro, String cidade, String CEP, String UF) {
        this.Rua = rua;
        this.Numero = numero;
        this.Bairro = bairro;
        this.Cidade = cidade;
        this.CEP = CEP;
        this.UF = UF;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int numero) {
        Numero = numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Address Register (Address adress) throws Exception {
        AddressDAO dao = FactoryDAO.createAddress();
        try {
            adress = dao.register(adress);
        }catch (Exception ex){
            System.out.println("Não foi possível inserir");;
        }
        return adress;
    }


    public String toString() {
        return Rua + " " + Numero + ", " + Bairro + ". " + Cidade + " - " + UF + ". " + CEP ;
    }
}
