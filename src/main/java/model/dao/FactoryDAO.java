package model.dao;


import services.database.ConnectionJDBC;

public class FactoryDAO {

    public static AddressDAO createAddress() {
        return new AddressDAO(ConnectionJDBC.getConnection());
    }

    public static ClientDAO createClient() {
        return new ClientDAO(ConnectionJDBC.getConnection());
    }

    public static DeliveryDAO createDelivery() {
        return new DeliveryDAO(ConnectionJDBC.getConnection());
    }

}
