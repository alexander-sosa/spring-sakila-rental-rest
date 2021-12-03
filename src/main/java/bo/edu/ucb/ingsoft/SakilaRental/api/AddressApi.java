package bo.edu.ucb.ingsoft.SakilaRental.api;

import bo.edu.ucb.ingsoft.SakilaRental.bl.AddressBL;
import bo.edu.ucb.ingsoft.SakilaRental.bl.RentBL;
import bo.edu.ucb.ingsoft.SakilaRental.bl.UserBL;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Address;
import bo.edu.ucb.ingsoft.SakilaRental.dto.City;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressApi {
    AddressBL addressBL;

    @Autowired
    public AddressApi(AddressBL addressBL){
        this.addressBL = addressBL;
    }

    @GetMapping(value = "/address/country", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Country> getCountries(){
        return addressBL.getCountries();
    }

    @GetMapping(value = "/address/city/{countryId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getCitiesFromCountry(@PathVariable(name = "countryId") Integer countryId){
        List<City> cities = addressBL.findCityByCountry(countryId);
        if(!cities.isEmpty())
            return new ResponseEntity<>(cities, HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> createAddress(@RequestBody Address address){
        List<Address> newAddress = addressBL.createAddress(address);
        if(!newAddress.isEmpty())
            return new ResponseEntity<>(newAddress, HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/address/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getSpecificAddress(@PathVariable(name = "id") Integer addressId){
        List<Address> address = addressBL.findAddressById(addressId);
        if(!address.isEmpty())
            return new ResponseEntity<>(address, HttpStatus.OK);
        return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
    }

}
