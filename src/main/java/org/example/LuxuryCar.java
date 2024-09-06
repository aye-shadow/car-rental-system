package org.example;

public class LuxuryCar extends Car implements Insurable {
    private static final double INSURANCE_COST_PERCENTAGE = 0.15;  // 15% of the base rent
    private static final double DAMAGE_COST_PERCENTAGE = 0.1;  // 10% of the total cost including luxury tax
    private static final double LUXURY_TAX_PERCENTAGE = 0.2;  // 20% luxury tax

    public LuxuryCar() {
        super(new CarDetails(3));
    }

    public LuxuryCar(String brand, String model, int year, double rentalFee) {
        super(brand, model, year, rentalFee);
    }

    @Override
    public double calculateRentalFee(double distance) {
        double baseCost = rentalFee + distance * 0.3;  // example calculation
        double luxuryTax = baseCost * LUXURY_TAX_PERCENTAGE;
        return baseCost + luxuryTax;
    }

    @Override
    public double calculateDamageCost() {
        double totalCost = calculateRentalFee(0);  // total cost including luxury tax
        return totalCost * DAMAGE_COST_PERCENTAGE;
    }

    @Override
    public double calculateInsuranceCost() {
        return rentalFee * INSURANCE_COST_PERCENTAGE;
    }
}