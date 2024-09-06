package org.example;

public abstract class Car {
    private static int nextCarID = 1;
    private static int nextPlateNumber = 1;

    protected String carID;
    protected String brand;
    protected String model;
    protected int year;
    protected boolean rentalStatus;
    protected double rentalFee;
    protected String plateNumber;

    public Car(CarDetails carDetails) {
        this.carID = "CAR" + nextCarID++;
        this.rentalStatus = false;
        this.plateNumber = "PLATE" + String.format("%03d", nextPlateNumber++);

        this.brand = carDetails.brand;
        this.model = carDetails.model;
        this.year = carDetails.year;
        this.rentalFee = carDetails.rentalFee;
    }

    public Car(String brand, String model, int year, double rentalFee) {
        this.carID = "CAR" + nextCarID++;
        this.rentalStatus = false;
        this.plateNumber = "PLATE" + String.format("%03d", nextPlateNumber++);

        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalFee = rentalFee;
    }

    public abstract double calculateRentalFee(double distance);
    public abstract double calculateDamageCost();

    // Getters and Setters
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public boolean isInsurable() {
        return this instanceof Insurable;
    }

    @Override
    public String toString() {
        return "CarID: " + carID + ", Brand: " + brand + ", Model: " + model + ", Year: " + year + ", Rental Status: " + rentalStatus + ", Rental Fee: " + rentalFee + ", Plate Number: " + plateNumber;
    }
}