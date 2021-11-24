package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.Film;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Store;
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
public class StoreDao {
    private DataSource dataSource;

    @Autowired
    public StoreDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Store> getStores(){
        String query = "SELECT s.store_id, c2.country " +
                "FROM store s " +
                "    LEFT JOIN address a on s.address_id = a.address_id " +
                "    LEFT JOIN city c on a.city_id = c.city_id " +
                "    LEFT JOIN country c2 on c.country_id = c2.country_id;";
        List<Store> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Store store = new Store();
                store.setStore_id(rs.getInt("store_id"));
                store.setCountry(rs.getString("country"));
                result.add(store);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Store> findStoreById(Integer store_id){
        String query = "SELECT s.store_id, c2.country " +
                "FROM store s " +
                "    LEFT JOIN address a on s.address_id = a.address_id " +
                "    LEFT JOIN city c on a.city_id = c.city_id " +
                "    LEFT JOIN country c2 on c.country_id = c2.country_id " +
                "WHERE " +
                "    s.store_id = ?;";
        List<Store> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, store_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Store store = new Store();
                store.setStore_id(rs.getInt("store_id"));
                store.setCountry(rs.getString("country"));
                result.add(store);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }
}
