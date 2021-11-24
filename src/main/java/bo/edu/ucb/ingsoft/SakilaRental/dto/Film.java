package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Objects;

public class Film {
    private Integer film_id;
    private String title;
    private String description;
    private Integer release_year;
    private String language;
    private String original_language;
    private Integer rental_duration;
    private Double rental_rate;
    private Integer length;
    private Double replacement_cost;
    private String rating;
    private String special_features; // todo: it was Integer?
    private Integer quantity; // todo: ?

    public Film(){

    }

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Integer getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(Integer rental_duration) {
        this.rental_duration = rental_duration;
    }

    public Double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(Double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(Double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(film_id, film.film_id) && Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(release_year, film.release_year) && Objects.equals(language, film.language) && Objects.equals(original_language, film.original_language) && Objects.equals(rental_duration, film.rental_duration) && Objects.equals(rental_rate, film.rental_rate) && Objects.equals(length, film.length) && Objects.equals(replacement_cost, film.replacement_cost) && Objects.equals(rating, film.rating) && Objects.equals(special_features, film.special_features) && Objects.equals(quantity, film.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film_id, title, description, release_year, language, original_language, rental_duration, rental_rate, length, replacement_cost, rating, special_features, quantity);
    }

    @Override
    public String toString() {
        return "Film{" +
                "film_id=" + film_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rental_duration=" + rental_duration +
                '}';
    }
}
