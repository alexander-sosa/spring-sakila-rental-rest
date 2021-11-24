package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.Address;
import bo.edu.ucb.ingsoft.SakilaRental.dto.City;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
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
                "       ST_AsText(a.location) as location " +
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

    public List<Country> getCountries(){
        String query = "SELECT co.country_id, co.country " +
                "FROM country co;";
        List<Country> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Country country = new Country();
                country.setCountry_id(rs.getInt("country_id"));
                country.setCountry(rs.getString("country"));
                result.add(country);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<City> findCityByCountry(Integer country_id){
        String query = "SELECT ci.city_id, ci.city, co.country_id " +
                "FROM city ci " +
                "     LEFT JOIN country co on co.country_id = ci.country_id " +
                "WHERE " +
                "    ci.country_id = ?;";
        List<City> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, country_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                City city = new City();
                city.setCity_id(rs.getInt("city_id"));
                city.setCountry_id(rs.getInt("country_id"));
                city.setCity(rs.getString("city"));
                result.add(city);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Address> createAddress(Address address){
        String query = "INSERT INTO address (address, address2, district,\n" +
                "                     city_id, postal_code, phone, location)\n" +
                "VALUES (?, ?, ?, ?,\n" +
                "        ?, ?, ST_GEOMFROMTEXT('POINT(1 1)'));";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ){
            pstmt.setString(1, address.getAddress());
            pstmt.setString(2, address.getAddress2());
            pstmt.setString(3, address.getDistrict());
            pstmt.setInt(4, address.getCity_id());
            pstmt.setString(5, address.getPostal_code());
            pstmt.setString(6, address.getPhone());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                int last_id = rs.getInt(1);
                return findAddressById(last_id);
            }
            return new ArrayList<>();
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
            return new ArrayList<>();
        }
    }
}
