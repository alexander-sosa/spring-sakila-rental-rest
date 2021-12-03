package bo.edu.ucb.ingsoft.SakilaRental.dao;

import bo.edu.ucb.ingsoft.SakilaRental.dto.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
                film.setQuantity(getMoviesInStock(store_id, rs.getInt("film_id"), rs.getInt("quantity")));
                result.add(film);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Film> getPremieres(Integer store_id){
        String query = "select f.film_id, f.title, f.description, f.release_year,\n" +
                "       l.name as language , ol.name as original_language, f.length,\n" +
                "       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost,\n" +
                "       count(*) as quantity\n" +
                "from film f\n" +
                "         LEFT JOIN language l ON ( f.language_id = l.language_id)\n" +
                "         LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)\n" +
                "         LEFT JOIN inventory i ON (f.film_id = i.film_id)\n" +
                "WHERE\n" +
                "        i.store_id = ?\n" +
                "GROUP BY f.film_id, i.last_update\n" +
                "order by i.last_update desc limit 10;";

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
                film.setQuantity(getMoviesInStock(store_id, rs.getInt("film_id"), rs.getInt("quantity")));
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
                film.setQuantity(getMoviesInStock(store_id, rs.getInt("film_id"), rs.getInt("quantity")));
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
                film.setQuantity(getMoviesInStock(store_id, rs.getInt("film_id"), rs.getInt("quantity")));
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
                film.setQuantity(getMoviesInStock(store_id, rs.getInt("film_id"), rs.getInt("quantity")));
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

    public Integer getMoviesInStock(Integer storeId, Integer filmId, Integer original){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        String query = "SELECT count(*) as rented\n" +
                "FROM inventory i2\n" +
                "         LEFT JOIN rental r on i2.inventory_id = r.inventory_id\n" +
                "WHERE\n" +
                "        i2.film_id = ?\n" +
                "  AND i2.store_id = ?\n" +
                "  AND return_date > ?;";
        Integer rented = 0;
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, filmId);
            pstmt.setInt(2, storeId);
            pstmt.setString(3, formatter.format(currentDate));
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                rented = rs.getInt("rented");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return original - rented;
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
                film.setQuantity(getMoviesInStock(store_id, rs.getInt("film_id"), rs.getInt("quantity")));
                result = film;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Film> getMostRentedMovies(Integer storeId){
        List<Film> result = new ArrayList<>();
        String query = "SELECT COUNT(r.inventory_id) as times_rented, i.inventory_id, i.film_id\n" +
                "FROM rental r\n" +
                "        LEFT JOIN inventory i on r.inventory_id = i.inventory_id\n" +
                "        LEFT JOIN staff s on s.staff_id = r.staff_id\n" +
                "WHERE s.staff_id = ?\n" +
                "GROUP BY r.inventory_id\n" +
                "ORDER BY times_rented desc limit 11;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, storeId);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Film film =getSpecificFilm(storeId, rs.getInt("film_id"));
                if(film.getTitle() != null)
                    result.add(film);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    public List<Film> getMostRentedMoviesByWeek(Integer storeId){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        List<Film> result = new ArrayList<>();
        List<Integer> shown = new ArrayList<>();
        String query = "SELECT COUNT(r.inventory_id) as times_rented, i.inventory_id, i.film_id\n" +
                "FROM rental r\n" +
                "        LEFT JOIN inventory i on r.inventory_id = i.inventory_id\n" +
                "        LEFT JOIN staff s on s.staff_id = r.staff_id\n" +
                "WHERE s.staff_id = ?\n" +
                "AND r.rental_date between ? and ?\n" +
                "GROUP BY r.inventory_id\n" +
                "ORDER BY times_rented desc limit 10";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, storeId);
            pstmt.setString(2, "2021-11-28");
            pstmt.setString(3, formatter.format(currentDate));
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
                Film film =getSpecificFilm(storeId, rs.getInt("film_id"));
                if(film.getTitle() != null && !inResults(film.getFilm_id(), shown)) {
                    result.add(film);
                    shown.add(film.getFilm_id());
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return result;
    }

    private Film getSpecificFilm(Integer storeId, Integer filmId){
        Film film = new Film();
        String query = "SELECT f.film_id, f.title, f.description, f.release_year,\n" +
                "       l.name as language , ol.name as original_language, f.length,\n" +
                "       f.rating, f.special_features, f.rental_duration, f.rental_rate, f.replacement_cost,\n" +
                "       count(*) as quantity\n" +
                "FROM film f\n" +
                "         LEFT JOIN language l ON ( f.language_id = l.language_id)\n" +
                "         LEFT JOIN language ol ON ( f.original_language_id = ol.language_id)\n" +
                "         LEFT JOIN inventory i ON (f.film_id = i.film_id)\n" +
                "WHERE\n" +
                "         i.store_id = ?\n" +
                "         and f.film_id = ?\n" +
                "GROUP BY f.film_id;";
        try(
                Connection conn = dataSource.getConnection();
                PreparedStatement pstmt =  conn.prepareStatement(query);
        ){
            pstmt.setInt(1, storeId);
            pstmt.setInt(2, filmId);
            ResultSet rs =  pstmt.executeQuery();
            while(rs.next()){
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
                film.setQuantity(getMoviesInStock(storeId, rs.getInt("film_id"), rs.getInt("quantity")));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            // TODO: gestionar correctamente la excepcion
        }
        return film;
    }

    private boolean inResults(Integer filmId, List<Integer> shown){
        for (int id: shown) {
            if (id == filmId)
                return true;
        }
        return false;
    }

}
