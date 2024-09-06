package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Renter {
    private static int nextRenterID = 1;
    private static final List<String> NAMES = Arrays.asList(
            "John Doe", "Jane Smith", "Alice Johnson", "Bob Brown", "Charlie Davis",
            "David Wilson", "Emma Thomas", "Frank White", "Grace Hall", "Henry King");
    private static final List<String> EMAILS = Arrays.asList(
            "john@example.com", "jane@example.com", "alice@example.com", "bob@example.com", "charlie@example.com",
            "david@example.com", "emma@example.com", "frank@example.com", "grace@example.com", "henry@example.com");
    private static final List<String> ADDRESSES = Arrays.asList(
            "123 Main St", "456 Elm St", "789 Oak St", "101 Maple St", "202 Pine St",
            "303 Cedar St", "404 Birch St", "505 Walnut St", "606 Cherry St", "707 Aspen St");

    protected String renterID;
    protected String name;
    protected String email;
    protected List<Car> rentedCars;
    protected double totalRentalFee;
    protected String phoneNumber;
    protected String address;

    public Renter() {
        this.name = NAMES.get(nextRenterID - 1);
        this.email = EMAILS.get(nextRenterID - 1);
        this.address = ADDRESSES.get(nextRenterID - 1);
        this.renterID = "RENT" + nextRenterID++;
        this.phoneNumber = generateRandomPhoneNumber();
        this.rentedCars = new ArrayList<>();
        this.totalRentalFee = 0.0;
    }

    public Renter(String name, String email, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.renterID = "RENT" + nextRenterID++;
        this.phoneNumber = phoneNumber;
        this.rentedCars = new ArrayList<>();
        this.totalRentalFee = 0.0;
    }

    private String generateRandomPhoneNumber() {
        Random random = new Random();
        int number = 100000000 + random.nextInt(900000000); // Generate a 9-digit number
        return "03" + number;
    }

    public abstract double calculateDiscountedFee(double baseFee);

    public void addToTotalRentalFee(double fee) {
        this.totalRentalFee += fee;
    }

    // Getters and Setters
    public String getRenterID() {
        return renterID;
    }

    public void setRenterID(String renterID) {
        this.renterID = renterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getRentedCars() {
        return rentedCars;
    }

    public void setRentedCars(List<Car> rentedCars) {
        this.rentedCars = rentedCars;
    }

    public double getTotalRentalFee() {
        return totalRentalFee;
    }

    public void setTotalRentalFee(double totalRentalFee) {
        this.totalRentalFee = totalRentalFee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "RenterID: " + renterID + ", Name: " + name + ", Email: " + email + ", Phone Number: " + phoneNumber
                + ", Address: " + address + ", Total Rental Fee: " + totalRentalFee;
    }
}