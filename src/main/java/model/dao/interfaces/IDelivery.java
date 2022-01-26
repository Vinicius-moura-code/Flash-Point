package model.dao.interfaces;

import model.entities.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDelivery<T> {
    Delivery register(Delivery delivery) throws Exception;

    //Filters
    ArrayList<T> findAll() throws Exception;

    public T findById(int id) throws Exception;

    public List<T> findByStatus(String stats) throws Exception;

    //Update
    public void updateStatus(int id, String status) throws SQLException;

    //DELETE
    public void DeleteD(int id);
}
