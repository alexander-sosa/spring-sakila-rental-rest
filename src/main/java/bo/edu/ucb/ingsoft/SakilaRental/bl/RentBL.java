package bo.edu.ucb.ingsoft.SakilaRental.bl;

import bo.edu.ucb.ingsoft.SakilaRental.dao.RentDao;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentBL {
    private final RentDao rentDao;

    @Autowired
    public RentBL(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    public boolean registerRent(Rent rent){
        return rentDao.registerRent(rent);
    }
}
