package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Objects;

public class Store {
    private Integer store_id;
    private String country;

    public Store(){

    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
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
        Store store = (Store) o;
        return Objects.equals(store_id, store.store_id) && Objects.equals(country, store.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(store_id, country);
    }

    @Override
    public String toString() {
        return "Store{" +
                "store_id=" + store_id +
                ", county='" + country + '\'' +
                '}';
    }
}
