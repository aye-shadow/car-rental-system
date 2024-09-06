package org.example;

public class SUV extends Car implements Insurable {
    private static final double INSURANCE_COST_PERCENTAGE = 0.1;  // 10% of the base rent
    private static final double DAMAGE_COST_PERCENTAGE = 0.05;  // 5% of the base rent

    public SUV() {
        super(new CarDetails(2));
    }

    public SUV(String brand, String model, int year, double rentalFee) {
        super(brand, model, year, rentalFee);
    }

    @Override
    public double calculateRentalFee(double distance) {
        return rentalFee + distance * 0.2;  // example calculation
    }

    @Override
    public double calculateDamageCost() {
        return rentalFee * DAMAGE_COST_PERCENTAGE;
    }

    @Override
    public double calculateInsuranceCost() {
        return rentalFee * INSURANCE_COST_PERCENTAGE;
    }
}