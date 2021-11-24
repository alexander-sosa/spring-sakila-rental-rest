package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Objects;

public class City {
    private Integer city_id;
    private String city;
    private Integer country_id;

    public City(){

    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city1 = (City) o;
        return Objects.equals(city_id, city1.city_id) && Objects.equals(city, city1.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_id, city);
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", city='" + city + '\'' +
                ", country_id='" + country_id + '\'' +
                '}';
    }
}
