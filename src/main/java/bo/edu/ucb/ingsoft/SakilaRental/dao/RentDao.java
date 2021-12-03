package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.Address;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Inventory;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Payment;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RentDao {
    private final DataSource dataSource;

    @Autowired
    public RentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Rent> registerRent(Rent rent){
        String query = "INSERT INTO rental (rental_date, inventory_id, " +
                "                    customer_id, return_date, staff_id) " +
                "VALUES (?, ?, ?, ?, ?);";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setString(1, rent.getRental_date());
            pstmt.setInt(2, rent.getInventory_id());
            pstmt.setInt(3, rent.getCustomer_id());
            pstmt.setString(4, rent.getReturn_date());
            pstmt.setInt(5, 1);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int last_id = rs.getInt(1);
                return findRentById(last_id);
            }
            return new ArrayList<>();
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
            return new ArrayList<>();
        }
    }

    public List<Rent> findRentById(Integer rent_id){
        String query = "SELECT r.rental_id, r.rental_date, r.inventory_id,\n" +
                "       r.customer_id, r.return_date\n" +
                "FROM rental r\n" +
                "WHERE\n" +
                "    r.rental_id = ?;";
        List<Rent> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, rent_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Rent rent = new Rent();
                rent.setRental_id(rs.getInt("rental_id"));
                rent.setRental_date(rs.getString("rental_date"));
                rent.setInventory_id(rs.getInt("inventory_id"));
                rent.setCustomer_id(rs.getInt("customer_id"));
                rent.setReturn_date(rs.getString("return_date"));
                result.add(rent);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Payment> registerPayment(Payment payment){
        String query = "INSERT INTO payment (customer_id, staff_id, rental_id, amount, payment_date)\n" +
                "VALUES (?, ?, ?, ?, ?);";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setInt(1, payment.getCustomer_id());
            pstmt.setInt(2, 1);
            pstmt.setInt(3, payment.getRental_id());
            pstmt.setDouble(4, payment.getAmount());
            pstmt.setString(5, payment.getPayment_date());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int last_id = rs.getInt(1);
                return findPaymentById(last_id);
            }
            return new ArrayList<>();
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
            return new ArrayList<>();
        }
    }

    public List<Payment> findPaymentById(Integer payment_id){
        String query = "SELECT  p.payment_id, p.customer_id, p.rental_id,\n" +
                "       p.amount, p.payment_date\n" +
                "FROM payment p\n" +
                "WHERE p.payment_id = ?;";
        List<Payment> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, payment_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Payment payment = new Payment();
                payment.setPayment_id(rs.getInt("payment_id"));
                payment.setCustomer_id(rs.getInt("customer_id"));
                payment.setRental_id(rs.getInt("rental_id"));
                payment.setAmount(rs.getDouble("amount"));
                payment.setPayment_date(rs.getString("payment_date"));
                result.add(payment);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Inventory> getAvailableInventory(Inventory inventory){
        String query = "SELECT i.inventory_id\n" +
                "FROM inventory i\n" +
                "    LEFT JOIN film f on f.film_id = i.film_id\n" +
                "    LEFT JOIN store s on i.store_id = s.store_id\n" +
                "WHERE\n" +
                "    f.film_id = ?\n" +
                "    AND s.store_id = ?\n" +
                "    AND i.inventory_id NOT IN (\n" +
                "        SELECT i2.inventory_id\n" +
                "        FROM inventory i2\n" +
                "            LEFT JOIN rental r on i2.inventory_id = r.inventory_id\n" +
                "        WHERE\n" +
                "            i2.film_id = ?\n" +
                "            AND i2.store_id = ?\n" +
                "            AND return_date > ?\n" +
                "    );";
        List<Inventory> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, inventory.getFilm_id());
            pstmt.setInt(2, inventory.getStore_id());
            pstmt.setInt(3, inventory.getFilm_id());
            pstmt.setInt(4, inventory.getStore_id());
            pstmt.setString(5, inventory.getReturn_date());
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Inventory inventory1 = new Inventory();
                inventory1.setInventory_id(rs.getInt("inventory_id"));
                result.add(inventory1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

}
