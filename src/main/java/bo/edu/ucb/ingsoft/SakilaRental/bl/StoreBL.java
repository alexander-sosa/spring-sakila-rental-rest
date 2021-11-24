package bo.edu.ucb.ingsoft.SakilaRental.bl;

import bo.edu.ucb.ingsoft.SakilaRental.dao.StoreDao;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreBL {
    private final StoreDao storeDao;

    @Autowired
    public StoreBL(StoreDao storeDao) {
        this.storeDao = storeDao;
    }

    public List<Store> getStores(){
        return storeDao.getStores();
    }

    public List<Store> findStoreById(Integer storeId){
        return storeDao.findStoreById(storeId);
    }
}
