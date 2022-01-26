package model.dao;

import model.dao.interfaces.IClient;
import model.entities.Address;
import model.entities.Client;
import services.database.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO<T> implements IClient {
    private Connection conn;

    private static final String SELECT_ALL = "SELECT c.id, c.nome as client, c.cpf, c.client_address, e.rua, e.numero as num, e.bairro, e.cidade, e.cep, e.uf FROM client as c INNER JOIN address as e on c.client_address = e.id";
    private static final String SELECT_BY_CPF = "SELECT c.id, c.nome as client, c.cpf, c.client_address, e.rua, e.numero as num, e.bairro, e.cidade, e.cep, e.uf FROM client as c INNER JOIN address as e on c.client_address = e.id WHERE c.cpf = ?";
    private static final String INSERT = "INSERT INTO client (nome, cpf, produto, client_address) VALUES (?, ?, ?, ?)";

    public ClientDAO(Connection conn){
        this.conn = conn;
    }

    protected T createObject(ResultSet rs) throws SQLException {
        Client cli = new Client();
        cli.setEndereco(new Address());

        cli.setId(rs.getInt("id"));
        cli.setNome(rs.getString("client"));
        cli.setCpf(rs.getString("cpf"));

        cli.getEndereco().setId(rs.getInt("client_address"));
        cli.getEndereco().setRua(rs.getString("rua"));
        cli.getEndereco().setNumero(rs.getInt("num"));
        cli.getEndereco().setBairro(rs.getString("bairro"));
        cli.getEndereco().setCidade(rs.getString("cidade"));
        cli.getEndereco().setCEP(rs.getString("cep"));
        cli.getEndereco().setUF(rs.getString("uf"));


        return (T) cli;
    }


    @Override
    public Client register(Client client) throws Exception {
        PreparedStatement ps = null;

        try{
            ps  = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getNome());
            ps.setString(2, client.getCpf());
            ps.setString(3, client.getProduto());
            ps.setInt(4, client.getClient_address());

            int ra = ps.executeUpdate();

            if(ra > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    client.setId(id);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionJDBC.closeStatement(ps);
        }
        return client;
    }

    @Override
    public ArrayList<T> findAll() throws Exception {
        ArrayList<T> listResult = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SELECT_ALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listResult.add(this.createObject(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionJDBC.closeStatement(ps);
            System.out.println(System.lineSeparator());

        }

        return listResult;
    }

    @Override
    public T findByCpf(String cpf) throws Exception {
        PreparedStatement ps = null;
        T client = null;

        try {

            ps = conn.prepareStatement(SELECT_BY_CPF);
            ps.setString(1, cpf);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                client = this.createObject(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionJDBC.closeStatement(ps);
            System.out.println(System.lineSeparator());
        }
        return (T) client;
    }
}
