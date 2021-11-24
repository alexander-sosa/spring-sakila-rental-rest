package bo.edu.ucb.ingsoft.SakilaRental.dto;

import java.util.Objects;

public class Address {
    private Integer address_id;
    private String address;
    private String address2;
    private String district;
    private String city;
    private String country;
    private String postal_code;
    private String phone;
    private String location;

    public Address(){

    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address_id, address1.address_id) && Objects.equals(address, address1.address) && Objects.equals(address2, address1.address2) && Objects.equals(district, address1.district) && Objects.equals(city, address1.city) && Objects.equals(country, address1.country) && Objects.equals(postal_code, address1.postal_code) && Objects.equals(phone, address1.phone) && Objects.equals(location, address1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address_id, address, address2, district, city, country, postal_code, phone, location);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_id=" + address_id +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
