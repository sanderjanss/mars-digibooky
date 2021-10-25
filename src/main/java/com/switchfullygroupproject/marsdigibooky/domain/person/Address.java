package com.switchfullygroupproject.marsdigibooky.domain.person;

public class Address {
    private final String streetName;
    private final int houseNumber;
    private final String postalCode;
    private final String city;

    public Address(String streetName, int houseNumber, String postalCode, String city) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = cityNotNull(city);
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String cityNotNull(String city) {
        if(!(city == null)){
            return city;
        }
        throw new IllegalArgumentException("City cant be null.");
    }
}
