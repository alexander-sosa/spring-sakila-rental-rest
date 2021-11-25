package bo.edu.ucb.ingsoft.SakilaRental.api;

import bo.edu.ucb.ingsoft.SakilaRental.bl.*;
import bo.edu.ucb.ingsoft.SakilaRental.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SakilaApi {
    FilmsBL filmsBL;
    StoreBL storeBL;
    AddressBL addressBL;
    UserBL userBL;
    RentBL rentBL;

    @Autowired
    public SakilaApi(FilmsBL filmsBL, StoreBL storeBL, AddressBL addressBL, UserBL userBL, RentBL rentBL){
        this.filmsBL = filmsBL;
        this.storeBL = storeBL;
        this.addressBL = addressBL;
        this.userBL = userBL;
        this.rentBL = rentBL;
    }

    @GetMapping()
    public String helloWorld(){
        return "Welcome to Sakila Rental API!";
    }

    @GetMapping(value = "/film/{storeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getMovies(@PathVariable(name = "storeId") Integer storeId, @RequestParam("title")Optional<String> title, @RequestParam("actor") Optional<String> actor){
        List<Film> films;
        if(title.isPresent() && actor.isPresent()){
            films = filmsBL.findByActorAndTitle(storeId, actor.get(), title.get());
            if(films.size() != 0)
                return new ResponseEntity<>(films, HttpStatus.OK);
        }
        else if (title.isPresent()){
            films = filmsBL.findByTitle(storeId, title.get());
            if(films.size() != 0)
                return new ResponseEntity<>(films, HttpStatus.OK);
        }
        else if (actor.isPresent()){
            films = filmsBL.findByActor(storeId, actor.get());
            if(films.size() != 0)
                return new ResponseEntity<>(films, HttpStatus.OK);
        }
        else {
            films = filmsBL.getMovies(storeId);
            if(films.size() != 0) {
                return new ResponseEntity<>(films, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Film not found", HttpStatus.NOT_FOUND);
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

    @GetMapping(value = "/user/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getSpecificUser(@PathVariable(name = "userId") Integer userId){
        List<User> user = userBL.findUserById(userId);
        if(!user.isEmpty())
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createUser(@RequestBody User user){
        if(userBL.createUser(user))
            return new ResponseEntity<>("User created successfully", HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable(name = "userId") Integer user_id){
        user.setUser_id(user_id);
        if(userBL.updateUser(user))
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
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
}
