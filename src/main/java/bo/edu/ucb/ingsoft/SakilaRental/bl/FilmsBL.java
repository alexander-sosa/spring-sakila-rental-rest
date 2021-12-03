package bo.edu.ucb.ingsoft.SakilaRental.bl;

import bo.edu.ucb.ingsoft.SakilaRental.dao.FilmDao;
import bo.edu.ucb.ingsoft.SakilaRental.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilmsBL {
    private final FilmDao filmDao;

    @Autowired
    public FilmsBL(FilmDao filmDao){
        this.filmDao = filmDao;
    }

    public List<Film> getMovies(Integer storeId){
        return filmDao.getMovies(storeId);
    }

    public List<Film> getPremieres(Integer storeId){
        return filmDao.getPremieres(storeId);
    }

    public List<Film> findByTitle(Integer storeId, String title){
        return filmDao.findByTitle(storeId, title);
    }

    public List<Film> findByActor(Integer storeId, String actor){
        return filmDao.findByActor(storeId, actor);
    }

    public List<Film> findByActorAndTitle(Integer storeId, String actor, String title){
        return filmDao.findByActorAndTitle(storeId, actor, title);
    }

    public List<Film> getRandomMovies(Integer storeId){
        return filmDao.getRandomMovies(storeId);
    }

    public List<Film> getMostRented(Integer storeId){
        return filmDao.getMostRentedMovies(storeId);
    }

    public List<Film> getMostRentedByWeek(Integer storeId){
        return filmDao.getMostRentedMoviesByWeek(storeId);
    }
}
