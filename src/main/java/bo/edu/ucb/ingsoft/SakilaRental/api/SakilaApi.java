package bo.edu.ucb.ingsoft.SakilaRental.api;

import bo.edu.ucb.ingsoft.SakilaRental.bl.*;
import bo.edu.ucb.ingsoft.SakilaRental.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SakilaApi {
    @Autowired
    public SakilaApi(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @GetMapping()
    public String helloWorld(){
        return "Welcome to Sakila Rental API!";
    }

    private final JavaMailSender mailSender;

    @PostMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> sendEmail(@RequestBody User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sakila@sakila.org");
        message.setTo(user.getEmail());
        message.setSubject("Films rental completed!");
        message.setText("Thanks for your purchase " + user.getFirst_name() + " " + user.getLast_name() + "! Your films are on the way to your address.");
        mailSender.send(message);
        return new ResponseEntity<>("Mail sent", HttpStatus.OK);
    }
}
