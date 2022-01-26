package model.dao;

import model.dao.interfaces.IAddress;
import model.entities.Address;
import services.database.ConnectionJDBC;

import java.sql.*;


public class AddressDAO implements IAddress {
    private Connection conn;

    private static final String INSERT = "INSERT INTO address (rua, numero, bairro, cidade, cep, uf) VALUES (?, ?, ?, ?, ?, ?)";

    public AddressDAO(Connection conn){
        this.conn = conn;
    }
    @Override
    public Address register(Address address) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, address.getRua());
            ps.setInt(2, address.getNumero());
            ps.setString(3, address.getBairro());
            ps.setString(4, address.getCidade());
            ps.setString(5, address.getCEP());
            ps.setString(6, address.getUF());

            int ra = ps.executeUpdate();
            if(ra > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    address.setId(id);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectionJDBC.closeStatement(ps);
        }
        return address;
    }

    @Override
    public Address Take(Address address) throws Exception {
        try {

        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {

        }
        return null;
    }
}
