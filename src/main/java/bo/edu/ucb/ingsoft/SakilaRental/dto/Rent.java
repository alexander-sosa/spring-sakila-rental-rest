package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Date;
import java.util.Objects;

public class Rent {
    private Integer rental_id;
    private String rental_date;
    private String rental_date_string;
    private Integer inventory_id;
    private Integer customer_id;
    private String return_date;
    private String return_date_string;

    public Rent(){

    }

    public Integer getRental_id() {
        return rental_id;
    }

    public void setRental_id(Integer rental_id) {
        this.rental_id = rental_id;
    }

    public String getRental_date() {
        return rental_date;
    }

    public void setRental_date(String rental_date) {
        this.rental_date = rental_date;
        this.rental_date_string = rental_date.toString();
    }

    public Integer getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
        this.return_date_string = return_date.toString();
    }

    public String getRental_date_string() {
        return rental_date_string;
    }

    public void setRental_date_string(String rental_date_string) {
        this.rental_date_string = rental_date_string;
    }

    public String getReturn_date_string() {
        return return_date_string;
    }

    public void setReturn_date_string(String return_date_string) {
        this.return_date_string = return_date_string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return Objects.equals(rental_date, rent.rental_date) && Objects.equals(inventory_id, rent.inventory_id) && Objects.equals(customer_id, rent.customer_id) && Objects.equals(return_date, rent.return_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rental_date, inventory_id, customer_id, return_date);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "rental_date=" + rental_date +
                ", rental_date(String)=" + rental_date_string +
                ", inventory_id=" + inventory_id +
                ", customer_id=" + customer_id +
                ", return_date=" + return_date +
                ", return_date(String)=" + return_date_string +
                '}';
    }
}
