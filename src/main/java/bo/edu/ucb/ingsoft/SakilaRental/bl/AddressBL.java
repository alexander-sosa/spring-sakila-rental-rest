package bo.edu.ucb.ingsoft.SakilaRental.bl;

import bo.edu.ucb.ingsoft.SakilaRental.dao.AddressDao;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Address;
import bo.edu.ucb.ingsoft.SakilaRental.dto.City;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressBL {
    private final AddressDao addressDao;

    @Autowired
    public AddressBL(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public List<Address> findAddressById(Integer addressID){
        return addressDao.findAddressById(addressID);
    }

    public List<Country> getCountries(){
        return addressDao.getCountries();
    }

    public List<City> findCityByCountry(Integer countryId){
        return addressDao.findCityByCountry(countryId);
    }

    public List<Address> createAddress(Address address){
        return addressDao.createAddress(address);
    }
}
