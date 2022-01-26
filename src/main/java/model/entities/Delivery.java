package model.entities;

import model.dao.DeliveryDAO;
import model.dao.FactoryDAO;
import model.entities.interfaces.IDelivery;

import java.util.ArrayList;
import java.util.List;

public class Delivery implements IDelivery {

    private int id;
    private String status;
    private Client client;
    private int delivery_client;

    public Delivery(){}

    public Delivery(int id, String cpf, String status, int delivery_client) {
        this.id = id;
        this.status = status;
        this.delivery_client = delivery_client;
    }

    public Delivery( Client client, int delivery_client) {
        this.client = client;
        this.delivery_client = delivery_client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getDelivery_client() {
        return delivery_client;
    }

    public void setDelivery_client(int delivery_client) {
        this.delivery_client = delivery_client;
    }

    public Delivery Register (Delivery del) throws Exception {

        DeliveryDAO dao = FactoryDAO.createDelivery();

        try {
           del = dao.register(del);
        }catch (Exception ex){
            System.out.println("Não foi possivel incluir o registro.");
        }
        return del;
    }

    public List<Delivery> findAll() throws Exception {
        DeliveryDAO dao = FactoryDAO.createDelivery();
        ArrayList listResult = new ArrayList<>();
        try{
            listResult = dao.findAll();
        }catch (Exception e){

        }
        return listResult;
    }

    public Delivery findById(int id) throws Exception {
        DeliveryDAO dao = FactoryDAO.createDelivery();
        Delivery del = null;
        try{
            del = (Delivery) dao.findById(id);
        }catch (Exception e){

        }
        return del;
    }

    public List<Delivery> findStatus(String status) throws Exception {
        DeliveryDAO dao = FactoryDAO.createDelivery();
        ArrayList listResult = new ArrayList<>();
        try{
            listResult = dao.findByStatus(status);
        }catch (Exception e){

        }
        return listResult;
    }

    public void UpdateStatus(int id, String status){
        DeliveryDAO dao = FactoryDAO.createDelivery();
        try {
        dao.updateStatus(id, status);
        }catch (Exception e){

        }

    }
    public void DeleteDelivey(int id){
        DeliveryDAO dao = FactoryDAO.createDelivery();
        try {
            dao.DeleteD(id);
        }catch (Exception e){

        }
    }
    @Override
    public String toString() {
        return "Entrega  " + id + " | Status: " + status + " | Produto: " + client.getProduto() + " | Cliente: " + client.getNome() + " | CPF: " + client.getCpf() + " | CEP: " + client.getEndereco().getCEP();
    }

}
