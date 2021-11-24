package bo.edu.ucb.ingsoft.SakilaRental.bl;

import bo.edu.ucb.ingsoft.SakilaRental.dao.AddressDao;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Address;
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
}
