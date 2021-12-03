package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Date;

public class Inventory {
    private Integer inventory_id;
    private Integer film_id;
    private Integer store_id;
    private String return_date;
    private Date return_date_date;

    public Inventory() {
    }

    public Integer getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public Date getReturn_date_date() {
        return return_date_date;
    }

    public void setReturn_date_date(Date return_date_date) {
        this.return_date_date = return_date_date;
    }
}
