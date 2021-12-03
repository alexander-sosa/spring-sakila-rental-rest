package bo.edu.ucb.ingsoft.SakilaRental.api;

import bo.edu.ucb.ingsoft.SakilaRental.bl.AddressBL;
import bo.edu.ucb.ingsoft.SakilaRental.bl.RentBL;
import bo.edu.ucb.ingsoft.SakilaRental.bl.StoreBL;
import bo.edu.ucb.ingsoft.SakilaRental.bl.UserBL;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreApi {
    StoreBL storeBL;

    @Autowired
    public StoreApi(StoreBL storeBL){
        this.storeBL = storeBL;
    }

    @GetMapping(value = "/store", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Store> getStores(){
        return storeBL.getStores();
    }

    @GetMapping(value = "/store/{storeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getSpecificStore(@PathVariable(name = "storeId") Integer storeId){
        List<Store> store = storeBL.findStoreById(storeId);
        if(!store.isEmpty())
            return new ResponseEntity<>(store, HttpStatus.OK);
        return new ResponseEntity<>("Store not found", HttpStatus.NOT_FOUND);
    }

}
