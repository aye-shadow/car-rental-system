package org.example;

public class CompactCar extends Car {
    public CompactCar() {
        super(new CarDetails(1));
    }

    public CompactCar(String brand, String model, int year, double rentalFee) {
        super(brand, model, year, rentalFee);
    }

    @Override
    public double calculateRentalFee(double distance) {
        return rentalFee + distance * 0.1;  // example calculation
    }

    @Override
    public double calculateDamageCost() {
        return 0;  // Compact cars are not insurable
    }
}