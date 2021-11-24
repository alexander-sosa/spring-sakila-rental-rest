package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.Address;
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
public class AddressDao {
    private final DataSource dataSource;

    @Autowired
    public AddressDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Address> findAddressById(Integer address_id){
        String query = "SELECT a.address_id, a.address, a.address2, a.district, " +
                "       c.city, c2.country, a.postal_code, a.phone, " +
                "       a.location " +
                "FROM address a " +
                "    LEFT JOIN city c on a.city_id = c.city_id " +
                "    LEFT JOIN country c2 on c.country_id = c2.country_id " +
                "WHERE " +
                "    a.address_id = ?;";
        List<Address> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, address_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Address address = new Address();
                address.setAddress_id(rs.getInt("address_id"));
                address.setAddress(rs.getString("address"));
                address.setAddress2(rs.getString("address2"));
                address.setDistrict(rs.getString("district"));
                address.setCity(rs.getString("city"));
                address.setCountry(rs.getString("country"));
                address.setPostal_code(rs.getString("postal_code"));
                address.setPhone(rs.getString("phone"));
                address.setLocation(rs.getString("location"));
                result.add(address);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }
}
