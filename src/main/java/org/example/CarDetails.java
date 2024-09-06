package org.example;

import java.util.*;

public class CarDetails {
    public static final List<String> COMPACT_BRANDS = Arrays.asList("Toyota", "Honda", "Ford", "Chevrolet",
            "Volkswagen",
            "Hyundai", "Nissan");
    public static final List<String> SUV_BRANDS = Arrays.asList("Jeep", "Land Rover", "Kia", "Mazda");
    public static final List<String> LUXURY_BRANDS = Arrays.asList("Mercedes-Benz", "BMW", "Audi", "Lexus", "Jaguar",
            "Porsche", "Tesla", "Maserati", "Bentley", "Rolls-Royce");
    public static final List<String> MODELS = Arrays.asList("Model1", "Model2", "Model3", "Model4", "Model5");
    public static final List<Integer> YEARS = Arrays.asList(2019, 2020, 2021, 2022);
    public static final List<Double> RENTAL_FEES = Arrays.asList(28.0, 30.0, 32.0, 38.0, 40.0, 42.0);

    public String brand;
    public String model;
    public int year;
    public double rentalFee;

    public CarDetails(int carType) {
        Random random = new Random();

        if (carType == 1) {
            this.brand = COMPACT_BRANDS.get(random.nextInt(COMPACT_BRANDS.size()));
        } else if (carType == 2) {
            this.brand = SUV_BRANDS.get(random.nextInt(SUV_BRANDS.size()));
        } else {
            this.brand = LUXURY_BRANDS.get(random.nextInt(LUXURY_BRANDS.size()));
        }
        this.model = MODELS.get(random.nextInt(MODELS.size()));
        this.year = YEARS.get(random.nextInt(YEARS.size()));
        this.rentalFee = RENTAL_FEES.get(random.nextInt(RENTAL_FEES.size()));
        return;
    }
}