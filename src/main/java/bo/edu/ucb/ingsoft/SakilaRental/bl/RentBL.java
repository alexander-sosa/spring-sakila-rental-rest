package bo.edu.ucb.ingsoft.SakilaRental.bl;

import bo.edu.ucb.ingsoft.SakilaRental.dao.RentDao;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Inventory;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Payment;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentBL {
    private final RentDao rentDao;

    @Autowired
    public RentBL(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    public List<Rent> registerRent(Rent rent){
        return rentDao.registerRent(rent);
    }

    public List<Payment> registerPayment(Payment payment){
        return rentDao.registerPayment(payment);
    }

    public List<Inventory> getAvailableInventory(Inventory inventory){
        return rentDao.getAvailableInventory(inventory);
    }
}
