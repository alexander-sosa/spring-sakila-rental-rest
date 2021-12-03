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
public class FilmApi {
    FilmsBL filmsBL;

    @Autowired
    public FilmApi(FilmsBL filmsBL){
        this.filmsBL = filmsBL;
    }

    @GetMapping(value = "/film/{storeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getMovies(@PathVariable(name = "storeId") Integer storeId, @RequestParam("title") Optional<String> title, @RequestParam("actor") Optional<String> actor){
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

    @GetMapping(value = "/film/premiere/{storeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getPremieres(@PathVariable(name = "storeId") Integer storeId){
        List<Film> films;
        films = filmsBL.getPremieres(storeId);
        if(films.size() != 0)
            return new ResponseEntity<>(films, HttpStatus.OK);
        return new ResponseEntity<>("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/film/random/{storeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getRandomMovies(@PathVariable(name = "storeId") Integer storeId){
        List<Film> films;
        films = filmsBL.getRandomMovies(storeId);
        if(films.size() != 0)
            return new ResponseEntity<>(films, HttpStatus.OK);
        return new ResponseEntity<>("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/film/mostrented/{storeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getMostRented(@PathVariable(name = "storeId") Integer storeId){
        List<Film> films;
        films = filmsBL.getMostRented(storeId);
        if(films.size() != 0)
            return new ResponseEntity<>(films, HttpStatus.OK);
        return new ResponseEntity<>("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/film/mostrentedweek/{storeId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Object> getMostRentedByWeek(@PathVariable(name = "storeId") Integer storeId){
        List<Film> films;
        films = filmsBL.getMostRentedByWeek(storeId);
        if(films.size() != 0)
            return new ResponseEntity<>(films, HttpStatus.OK);
        return new ResponseEntity<>("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
