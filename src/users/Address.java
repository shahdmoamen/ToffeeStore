package users;

public class Address {
    private String country;
    private String  city;
    private String  street;
    private String zipCode;
    private String  building;
    private String  floor;
    private String  apartment;
    public Address(String country, String city, String street, String zipCode, String building, String floor, String apartment) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.building = building;
        this.floor = floor;
        this.apartment = apartment;
    }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getBuilding() {
        return building;
    }
    public String getFloor() {
        return floor;
    }
    public String getApartment() {
        return apartment;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

}
