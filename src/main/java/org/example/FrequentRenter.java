package org.example;

public class FrequentRenter extends Renter {
    private static final double DISCOUNT_RATE = 0.1; // 10% discount

    public FrequentRenter() {
    }

    public FrequentRenter(String name, String email, String address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }

    @Override
    public double calculateDiscountedFee(double baseFee) {
        return baseFee * (1 - DISCOUNT_RATE);
    }
}