package bo.edu.ucb.ingsoft.SakilaRental.api;

import bo.edu.ucb.ingsoft.SakilaRental.bl.RentBL;
import bo.edu.ucb.ingsoft.SakilaRental.bl.UserBL;
import bo.edu.ucb.ingsoft.SakilaRental.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserApi {
    UserBL userBL;
    @Autowired
    public UserApi(UserBL userBL){
        this.userBL = userBL;
    }

    @GetMapping(value = "/user/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getSpecificUser(@PathVariable(name = "userId") Integer userId){
        List<User> user = userBL.findUserById(userId);
        if(!user.isEmpty())
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> createUser(@RequestBody User user){
        List<User> createdUser = userBL.createUser(user);
        if(!createdUser.isEmpty())
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/user/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> login(@RequestBody User user){
        List<User> foundUser = userBL.findUserByEmail(user);
        if(!foundUser.isEmpty())
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable(name = "userId") Integer user_id){
        user.setUser_id(user_id);
        if(userBL.updateUser(user))
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        return new ResponseEntity<>("Invalid data supplied", HttpStatus.BAD_REQUEST);
    }

}
