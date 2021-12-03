package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDao {
    private DataSource dataSource;

    @Autowired
    public FilmDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Film> getMovies(Integer store_id){
        String query = "SELECT f.film_id, f.title, f.description, f.release_year, " +
                "       l.name as language , ol.name as original_language, f.length ," +
                "       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost, " +
                "       count(*) as quantity " +
                "FROM film f " +
                "         LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "         LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "         LEFT JOIN inventory i ON (f.film_id = i.film_id) " +
                "WHERE " +
                "         i.store_id = ? GROUP BY f.film_id; ";

        List<Film> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, store_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Film film = new Film();
                film.setFilm_id(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setRelease_year(rs.getInt("release_year"));
                film.setLanguage(rs.getString("language"));
                film.setOriginal_language(rs.getString("original_language"));
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecial_features(rs.getString("special_features"));
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getDouble("rental_rate"));
                film.setReplacement_cost(rs.getDouble("replacement_cost"));
                film.setQuantity(rs.getInt("quantity"));
                result.add(film);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Film> findByTitle(Integer store_id, String title){
        String query = "SELECT f.film_id, f.title, f.description, f.release_year, " +
                "       l.name as language , ol.name as original_language, f.length, " +
                "       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost, " +
                "       count(*) as quantity " +
                "FROM film f " +
                "       LEFT JOIN language l ON ( f.language_id = l.language_id) " +
                "       LEFT JOIN language ol ON ( f.original_language_id = ol.language_id) " +
                "       LEFT JOIN inventory i ON (f.film_id = i.film_id) " +
                "WHERE " +
                "       UPPER(title) LIKE (?) " +
                "       AND i.store_id = ? GROUP BY f.film_id;";

        List<Film> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setString(1, "%" + title.toUpperCase() + "%");
            pstmt.setInt(2, store_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Film film = new Film();
                film.setFilm_id(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setRelease_year(rs.getInt("release_year"));
                film.setLanguage(rs.getString("language"));
                film.setOriginal_language(rs.getString("original_language"));
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecial_features(rs.getString("special_features"));
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getDouble("rental_rate"));
                film.setReplacement_cost(rs.getDouble("replacement_cost"));
                film.setQuantity(rs.getInt("quantity"));
                result.add(film);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Film> findByActor(Integer store_id, String actor){
        String query = "SELECT f.film_id, f.title, f.description, f.release_year, " +
                "       l.name as language , ol.name as original_language, f.length, " +
                "       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost, " +
                "       count(*) as quantity " +
                "FROM film f " +
                "        LEFT JOIN language l ON (f.language_id = l.language_id) " +
                "        LEFT JOIN language ol ON (f.original_language_id = ol.language_id) " +
                "        LEFT JOIN film_actor fa on (f.film_id = fa.film_id) " +
                "        LEFT JOIN actor a on (fa.actor_id = a.actor_id) " +
                "        LEFT JOIN inventory i ON (f.film_id = i.film_id) " +
                "WHERE " +
                "        UPPER(CONCAT(a.first_name, ' ', a.last_name)) LIKE (?) " +
                "        AND i.store_id = ? GROUP BY f.film_id;";

        List<Film> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setString(1, "%" + actor.toUpperCase() + "%");
            pstmt.setInt(2, store_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Film film = new Film();
                film.setFilm_id(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setRelease_year(rs.getInt("release_year"));
                film.setLanguage(rs.getString("language"));
                film.setOriginal_language(rs.getString("original_language"));
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecial_features(rs.getString("special_features"));
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getDouble("rental_rate"));
                film.setReplacement_cost(rs.getDouble("replacement_cost"));
                film.setQuantity(rs.getInt("quantity"));
                result.add(film);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Film> findByActorAndTitle(Integer store_id, String actor, String title){
        String query = "SELECT f.film_id, f.title, f.description, f.release_year, " +
                "       l.name as language , ol.name as original_language, f.length, " +
                "       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost, " +
                "       count(*) as quantity " +
                "FROM film f " +
                "         LEFT JOIN language l ON (f.language_id = l.language_id) " +
                "         LEFT JOIN language ol ON (f.original_language_id = ol.language_id) " +
                "         LEFT JOIN film_actor fa on (f.film_id = fa.film_id) " +
                "         LEFT JOIN actor a on (fa.actor_id = a.actor_id) " +
                "         LEFT JOIN inventory i ON (f.film_id = i.film_id) " +
                "WHERE " +
                "        UPPER(CONCAT(a.first_name, ' ', a.last_name)) LIKE (?) " +
                "        AND UPPER(f.title) LIKE (?) " +
                "        AND i.store_id = ? GROUP BY f.film_id;";

        List<Film> result = new ArrayList<>();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setString(1, "%" + actor.toUpperCase() + "%");
            pstmt.setString(2, "%" + title.toUpperCase() + "%");
            pstmt.setInt(3, store_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Film film = new Film();
                film.setFilm_id(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setRelease_year(rs.getInt("release_year"));
                film.setLanguage(rs.getString("language"));
                film.setOriginal_language(rs.getString("original_language"));
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecial_features(rs.getString("special_features"));
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getDouble("rental_rate"));
                film.setReplacement_cost(rs.getDouble("replacement_cost"));
                film.setQuantity(rs.getInt("quantity"));
                result.add(film);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Film> getRandomMovies(Integer store_id){
        List<Film> result = new ArrayList<>();
        for(int i = 0; i<10 ; i++){
            result.add(packRandomMovie(store_id));
        }
        return result;
    }

    public Film packRandomMovie(Integer store_id){
        String query = "SELECT f.film_id, f.title, f.description, f.release_year,\n" +
                "       l.name as language , ol.name as original_language, f.length,\n" +
                "       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost,\n" +
                "       count(*) as quantity\n" +
                "FROM film f\n" +
                "         LEFT JOIN language l ON ( f.language_id = l.language_id)\n" +
                "         LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)\n" +
                "         LEFT JOIN inventory i ON (f.film_id = i.film_id)\n" +
                "WHERE\n" +
                "        i.store_id = ?\n" +
                "GROUP BY f.film_id\n" +
                "ORDER BY rand()\n" +
                "LIMIT 1;";

        Film result = new Film();
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, store_id);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Film film = new Film();
                film.setFilm_id(rs.getInt("film_id"));
                film.setTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setRelease_year(rs.getInt("release_year"));
                film.setLanguage(rs.getString("language"));
                film.setOriginal_language(rs.getString("original_language"));
                film.setLength(rs.getInt("length"));
                film.setRating(rs.getString("rating"));
                film.setSpecial_features(rs.getString("special_features"));
                film.setRental_duration(rs.getInt("rental_duration"));
                film.setRental_rate(rs.getDouble("rental_rate"));
                film.setReplacement_cost(rs.getDouble("replacement_cost"));
                film.setQuantity(rs.getInt("quantity"));
                result = film;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

}
