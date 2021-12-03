package bo.edu.ucb.ingsoft.SakilaRental.api;

import bo.edu.ucb.ingsoft.SakilaRental.bl.RentBL;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Inventory;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Payment;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentApi {
    RentBL rentBL;

    @Autowired
    public RentApi(RentBL rentBL){
        this.rentBL = rentBL;
    }

    @PostMapping(value = "/rent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> registerRent(@RequestBody Rent rent){
        List<Rent> registeredRent = rentBL.registerRent(rent);
        if(!registeredRent.isEmpty())
            return new ResponseEntity<>(registeredRent, HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> registerPayment(@RequestBody Payment payment){
        List<Payment> registeredPayment = rentBL.registerPayment(payment);
        if(!registeredPayment.isEmpty())
            return new ResponseEntity<>(registeredPayment, HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/rent/inventory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getAvailableInventory(@RequestBody Inventory inventory){
        List<Inventory> foundInventory = rentBL.getAvailableInventory(inventory);
        if(!foundInventory.isEmpty())
            return new ResponseEntity<>(foundInventory, HttpStatus.OK);
        return new ResponseEntity<>("No inventory found", HttpStatus.BAD_REQUEST);
    }
}
