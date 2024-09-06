package org.example;

import java.util.Date;

public class RentalTransaction {
    private static int nextTransactionID = 1;

    private String transactionID;
    private Renter renter;
    private Car car;
    private Date rentalDate;
    private Date returnDate;
    private boolean insuranceAdded;
    private double distance;
    private double rentalFee;
    private double damageCost;
    private boolean isDamaged;

    public RentalTransaction(Renter renter, Car car, boolean insuranceAdded) {
        this.transactionID = "TRANS" + nextTransactionID++;
        this.rentalDate = new Date();
        this.returnDate = null;

        this.renter = renter;
        this.car = car;
        this.insuranceAdded = insuranceAdded;
        this.damageCost = 0.0;
        this.damageCost = 0.0;
    }

    public void calculateTotalRentalCost(double distance) {
        double baseFee = car.calculateRentalFee(distance);
        double discountedFee = renter.calculateDiscountedFee(baseFee);
        damageCost = discountedFee;
        if (insuranceAdded && car instanceof Insurable) {
            damageCost += ((Insurable) car).calculateInsuranceCost();
        }
    }

    public void calculateDamageCost() {
        if (insuranceAdded && car instanceof Insurable) {
            damageCost = car.calculateDamageCost() - ((Insurable) car).calculateInsuranceCost();
            if (damageCost < 50) {  // Minimum damage cost if insured
                damageCost = 50;
            }
        } else {
            damageCost = car.calculateDamageCost();
        }
    }

    public void returnCar(Date returnDate) {
        this.returnDate = returnDate;
        car.setRentalStatus(false);
        renter.getRentedCars().remove(car);
    }

    // Getters and Setters
    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isInsuranceAdded() {
        return insuranceAdded;
    }

    public void setInsuranceAdded(boolean insuranceAdded) {
        this.insuranceAdded = insuranceAdded;
    }

    public double getTotalRentalCost() {
        return damageCost;
    }

    public void setTotalRentalCost(double totalRentalCost) {
        this.damageCost = totalRentalCost;
    }

    public double getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(double damageCost) {
        this.damageCost = damageCost;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public void setDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }

    @Override
    public String toString() {
        return "TransactionID: " + transactionID + ", Renter: " + renter.getName() + ", Car: " + car.getBrand() + " " + car.getModel() + ", Rental Date: " + rentalDate + ", Return Date: " + returnDate + ", Insurance Added: " + insuranceAdded + ", Total Rental Cost: " + damageCost + ", Damage Cost: " + damageCost;
    }
}