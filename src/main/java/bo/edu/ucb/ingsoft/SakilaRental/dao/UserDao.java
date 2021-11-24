package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private final DataSource dataSource;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findUserById(Integer user_id){
        String query = "SELECT c.customer_id, c.store_id, c.first_name, " +
                "       c.last_name, c.email, c.address_id, c.active, " +
                "       c.create_date " +
                "FROM customer c " +
                "WHERE " +
                "    c.customer_id = ?;";
        List<User> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, user_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setUser_id(rs.getInt("customer_id"));
                user.setStore_id(rs.getInt("store_id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setAddress_id(rs.getInt("address_id"));
                user.setActive(rs.getInt("active"));
                java.sql.Date createDate = rs.getDate("create_date");
                user.setCreate_date(new java.util.Date(createDate.getTime()));
                result.add(user);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public boolean createUser(User user){
        String query = "INSERT INTO customer (store_id, first_name, last_name, " +
                "                      email, address_id, active, create_date, " +
                "                      last_update) " +
                "VALUES (?, ?, ?, ?, " +
                "        ?, ?, ?, ?); ";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, user.getStore_id());
            pstmt.setString(2, user.getFirst_name());
            pstmt.setString(3, user.getLast_name());
            pstmt.setString(4, user.getEmail());
            pstmt.setInt(5, user.getAddress_id());
            pstmt.setInt(6, 1);
            pstmt.setString(7, "2021-11-23 00:29:45");
            pstmt.setString(8, "2021-11-23 00:29:45");

            System.out.println("estoy por ejecutar");
            boolean success = pstmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
            System.out.println("entre en el catch, retorno false");
            return false;
        }
    }

    public boolean updateUser(User user){
        String query = "UPDATE customer " +
                "SET store_id = ?, first_name = ?, last_name = ?, " +
                "    email = ?, address_id = ? " +
                "WHERE customer_id = ?;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, user.getStore_id());
            pstmt.setString(2, user.getFirst_name());
            pstmt.setString(3, user.getLast_name());
            pstmt.setString(4, user.getEmail());
            pstmt.setInt(5, user.getAddress_id());
            pstmt.setInt(6, user.getUser_id());

            boolean success = pstmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
            return false;
        }
    }
}
