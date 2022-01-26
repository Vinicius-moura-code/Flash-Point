package model.dao.interfaces;

import model.entities.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IClient<T> {
    Client register(Client client) throws Exception;
    ArrayList<T> findAll() throws Exception;
     T findByCpf(String cpf) throws Exception;
}
