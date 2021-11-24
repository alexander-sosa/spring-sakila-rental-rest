package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Objects;

public class Country {
    private Integer country_id;
    private String country;

    public Country(){

    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country1 = (Country) o;
        return Objects.equals(country_id, country1.country_id) && Objects.equals(country, country1.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country_id, country);
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_id=" + country_id +
                ", country='" + country + '\'' +
                '}';
    }
}
