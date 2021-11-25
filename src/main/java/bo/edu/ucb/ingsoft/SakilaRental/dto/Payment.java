package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Date;
import java.util.Objects;

public class Payment {
    private Integer payment_id;
    private Integer customer_id;
    private Integer rental_id;
    private Double amount;
    private Date payment_date_date;
    private String payment_date;

    public Payment(){

    }

    public Integer getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Integer payment_id) {
        this.payment_id = payment_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public Integer getRental_id() {
        return rental_id;
    }

    public void setRental_id(Integer rental_id) {
        this.rental_id = rental_id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPayment_date_date() {
        return payment_date_date;
    }

    public void setPayment_date_date(Date payment_date_date) {
        this.payment_date_date = payment_date_date;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(payment_id, payment.payment_id) && Objects.equals(customer_id, payment.customer_id) && Objects.equals(rental_id, payment.rental_id) && Objects.equals(amount, payment.amount) && Objects.equals(payment_date_date, payment.payment_date_date) && Objects.equals(payment_date, payment.payment_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment_id, customer_id, rental_id, amount, payment_date_date, payment_date);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payment_id=" + payment_id +
                ", customer_id=" + customer_id +
                ", rental_id=" + rental_id +
                ", amount=" + amount +
                ", payment_date_date=" + payment_date_date +
                ", payment_date='" + payment_date + '\'' +
                '}';
    }
}
