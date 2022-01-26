package model.dao;

import Enums.Status;
import model.dao.interfaces.IDelivery;
import model.entities.Address;
import model.entities.Client;
import model.entities.Delivery;
import services.database.ConnectionJDBC;

import java.sql.*;
import java.util.ArrayList;

public class DeliveryDAO<T> implements IDelivery {
    private final Connection conn;

    private static final String INSERT = "INSERT INTO delivery (status, delivery_client) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT d.id, d.delivery_client, d.status, c.nome AS client, c.cpf, c.produto AS product, c.client_address, e.cep FROM delivery AS d INNER JOIN client AS c ON d.delivery_client = c.id INNER JOIN address AS e ON c.client_address = e.id WHERE d.status != 'INATIVADO'";
    private static final String SELECT_BY_ID = "SELECT d.id, d.delivery_client, d.status, c.nome AS client, c.cpf, c.produto AS product, c.client_address, e.cep FROM delivery AS d INNER JOIN client AS c ON d.delivery_client = c.id INNER JOIN address AS e ON c.client_address = e.id WHERE d.id = ?";
    private static final String SELECT_BY_STATUS = "SELECT d.id, d.delivery_client, d.status, c.nome AS client, c.cpf, c.produto AS product, c.client_address, e.cep FROM delivery AS d INNER JOIN client AS c ON d.delivery_client = c.id INNER JOIN address AS e ON c.client_address = e.id WHERE status = ?";
    private static final String UPDATE = "UPDATE delivery SET status = ?  WHERE id = ?";
    private static final String DELETE = "DELETE d, c, a FROM delivery AS d INNER JOIN client AS c ON d.delivery_client = c.id INNER JOIN address AS a ON c.client_address = a.id WHERE d.id = ?";

    public DeliveryDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Delivery register(Delivery delivery) throws Exception {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(Status.SOLICITADO));
            ps.setInt(2, delivery.getDelivery_client());

            int ra = ps.executeUpdate();

            if (ra > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    delivery.setId(id);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(System.lineSeparator());
            ConnectionJDBC.closeStatement(ps);
        }
        return delivery;
    }

    protected T createObject(ResultSet rs) throws SQLException {
        Delivery del = new Delivery();
        del.setClient(new Client());
        del.getClient().setEndereco(new Address());


        del.setId(rs.getInt("id"));
        del.getClient().setId(rs.getInt("delivery_client"));
        del.setStatus(rs.getString("status"));

        del.getClient().setNome(rs.getString("client"));
        del.getClient().setCpf(rs.getString("cpf"));
        del.getClient().setProduto(rs.getString("product"));

        del.getClient().getEndereco().setId(rs.getInt("client_address"));
        del.getClient().getEndereco().setCEP(rs.getString("cep"));
        return (T) del;
    }

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
    public T findById(int id) throws Exception {
        PreparedStatement ps = null;
        T delivery = null;

        try {

            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                delivery = this.createObject(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionJDBC.closeStatement(ps);
            System.out.println(System.lineSeparator());
        }
        return delivery;
    }

    @Override
    public ArrayList<T> findByStatus(String stats) throws Exception {
        PreparedStatement ps = null;

        ArrayList<T> listResult = new ArrayList<>();

        try {
            ps = conn.prepareStatement(SELECT_BY_STATUS);
            ps.setString(1, stats);

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

    public void updateStatus(int id, String status) throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(UPDATE);

            ps.setInt(2, id);
            ps.setString(1, status);

            int ra = ps.executeUpdate();
            if (ra > 0) {
                System.out.println("Status atualizado com sucesso;");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionJDBC.closeStatement(ps);
            System.out.println(System.lineSeparator());
        }

    }

    public void DeleteD(int id) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(DELETE);

            ps.setInt(1, id);

            int ra = ps.executeUpdate();

            if (ra > 0) System.out.println("Item excluído com êxito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionJDBC.closeStatement(ps);
            System.out.println(System.lineSeparator());
        }

    }
}
