package org.example;

public class RegularRenter extends Renter {
    public RegularRenter() {
    }

    public RegularRenter(String name, String email, String address, String phoneNumber) {
        super(name, email, address, phoneNumber);
    }

    @Override
    public double calculateDiscountedFee(double baseFee) {
        return baseFee;  // No discount for regular renters
    }
}