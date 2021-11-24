package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class RentDao {
    private final DataSource dataSource;

    @Autowired
    public RentDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean registerRent(Rent rent){
        String query = "INSERT INTO rental (rental_date, inventory_id, " +
                "                    customer_id, return_date, staff_id) " +
                "VALUES (?, ?, ?, ?, ?);";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setString(1, rent.getRental_date_string());
            pstmt.setInt(2, rent.getInventory_id());
            pstmt.setInt(3, rent.getCustomer_id());
            pstmt.setString(4, rent.getReturn_date_string());
            pstmt.setInt(5, 1);

            boolean success = pstmt.execute();
            return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
            return false;
        }
    }
}
