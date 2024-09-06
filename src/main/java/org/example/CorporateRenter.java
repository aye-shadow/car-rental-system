package org.example;

public class CorporateRenter extends Renter {
    private static final double CORPORATE_DISCOUNT_RATE = 0.15;  // 15% discount

    public CorporateRenter() {
    }

    public CorporateRenter(String name, String email, String address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }

    @Override
    public double calculateDiscountedFee(double baseFee) {
        return baseFee * (1 - CORPORATE_DISCOUNT_RATE);
    }
}