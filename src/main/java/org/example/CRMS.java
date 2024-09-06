package org.example;

import java.util.*;

public class CRMS {
    private static final List<String> CAR_BRANDS = Arrays.asList(
        "Toyota", "Honda", "Ford", "Chevrolet", "BMW", "Mercedes", "Audi", "Volkswagen", "Hyundai", "Nissan"
    );
    private List<Car> cars;
    private List<Renter> renters;
    private List<RentalTransaction> transactions;

    public CRMS() {
        cars = new ArrayList<>();
        renters = new ArrayList<>();
        transactions = new ArrayList<>();

        Random random = new Random();
        int totalCars = random.nextInt(10) + 1;
        for (int i = 0; i < totalCars; i++) {
            int carType = random.nextInt(3) + 1;
            if (carType == 1) {
                cars.add(new CompactCar());
            } 
            else if (carType == 2) {
                cars.add(new SUV());
            } 
            else {
                cars.add(new LuxuryCar());
            }
        }    

        int totalRenters = random.nextInt(10) + 1;
        for (int i = 0; i < totalRenters; i++) {
            int renterType = random.nextInt(3) + 1;
            if (renterType == 1) {
                renters.add(new FrequentRenter());
            } else if (renterType == 2) {
                renters.add(new RegularRenter());
            }
            else {
                renters.add(new CorporateRenter());
            }
        }    
    }

    // Car Management
    public void addCar(int carType, String brand, String model, int year, double rentalFee) {
        switch(carType) {
            case 1:
                cars.add(new CompactCar(brand, model, year, rentalFee));
            break;
            case 2:
                cars.add(new SUV(brand, model, year, rentalFee));
            break;
            case 3:
                cars.add(new LuxuryCar(brand, model, year, rentalFee));
            break;
        }
    }

    public void displayAllCars() {
        String leftAlignFormat = "| %-10s | %-11s | %-15s | %-10s | %-4d | %-10.2f | %-10s | %-8s |%n";
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
        System.out.format("| CarID      | PlateNumber | Brand           | Model      | Year | Rental Fee | Type       | Rented   |%n");
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
        for (Car car : cars) {
            String carType = getCarType(car);
            String rentalStatus = car.isRentalStatus() ? "Yes" : "No";
            System.out.format(leftAlignFormat, car.getCarID(), car.getPlateNumber(), car.getBrand(), car.getModel(), car.getYear(), car.getRentalFee(), carType, rentalStatus);
        }
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
    }
    
    private String getCarType(Car car) {
        if (CarDetails.SUV_BRANDS.contains(car.getBrand())) {
            return "SUV";
        } else if (CarDetails.LUXURY_BRANDS.contains(car.getBrand())) {
            return "Luxury";
        } else if (CarDetails.COMPACT_BRANDS.contains(car.getBrand())) {
            return "Compact";
        } else {
            return "Unknown";
        }
    }

    public void displayCarByID(String carID) {
        Car car = findCarByID(carID);
        if (car != null) {
            String leftAlignFormat = "| %-10s | %-11s | %-15s | %-10s | %-4d | %-10.2f | %-10s | %-8s |%n";
            System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
            System.out.format("| CarID      | PlateNumber | Brand           | Model      | Year | Rental Fee | Type       | Rented   |%n");
            System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
            String carType = getCarType(car);
            String rentalStatus = car.isRentalStatus() ? "Yes" : "No";
            System.out.format(leftAlignFormat, car.getCarID(), car.getPlateNumber(), car.getBrand(), car.getModel(), car.getYear(), car.getRentalFee(), carType, rentalStatus);
            System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
        } else {
            System.out.println("Car with ID " + carID + " not found.");
        }
    }

    public void displayCarByBrand(String brand) {
        String leftAlignFormat = "| %-10s | %-11s | %-15s | %-10s | %-4d | %-10.2f | %-10s | %-8s |%n";
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
        System.out.format("| CarID      | PlateNumber | Brand           | Model      | Year | Rental Fee | Type       | Rented   |%n");
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(brand)) {
                String carType = getCarType(car);
                String rentalStatus = car.isRentalStatus() ? "Yes" : "No";
                System.out.format(leftAlignFormat, car.getCarID(), car.getPlateNumber(), car.getBrand(), car.getModel(), car.getYear(), car.getRentalFee(), carType, rentalStatus);
            }
        }
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
    }

    public void displayCarByType(String type) {
        String leftAlignFormat = "| %-10s | %-11s | %-15s | %-10s | %-4d | %-10.2f | %-10s | %-8s |%n";
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
        System.out.format("| CarID      | PlateNumber | Brand           | Model      | Year | Rental Fee | Type       | Rented   |%n");
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
        for (Car car : cars) {
            String carType = getCarType(car);
            if (carType.equalsIgnoreCase(type)) {
                String rentalStatus = car.isRentalStatus() ? "Yes" : "No";
                System.out.format(leftAlignFormat, car.getCarID(), car.getPlateNumber(), car.getBrand(), car.getModel(), car.getYear(), car.getRentalFee(), carType, rentalStatus);
            }
        }
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+%n");
    }

    public String chooseCarBrands(int carType) {
        List<String> brands;
        switch (carType) {
            case 1:
                brands = CarDetails.COMPACT_BRANDS;
                break;
            case 2:
                brands = CarDetails.SUV_BRANDS;
                break;
            case 3:
                brands = CarDetails.LUXURY_BRANDS;
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                return "";
        }
        System.out.println("Car Brands:");
        for (int i = 0; i < brands.size(); i++) {
            System.out.println(i + ". " + brands.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\tChoose brand (enter index): ");
        int carBrandChoice = scanner.nextInt();
        if (carBrandChoice < 0 || carBrandChoice >= brands.size()) {
            System.out.println("\tInvalid choice. Please try again.");
            return "";
        }
        return brands.get(carBrandChoice);
    }

    public String chooseCarModels() {
        List<String> models = CarDetails.MODELS;
        System.out.println("\tCar Models:\n");
        for (int i = 0; i < models.size(); i++) {
            System.out.println("\t" + i + ". " + models.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\tChoose model (enter index): ");
        int carModelChoice = scanner.nextInt();
        if (carModelChoice < 0 || carModelChoice >= models.size()) {
            System.out.println("Invalid choice. Please try again.");
            return "";
        }
        return models.get(carModelChoice);
    }

    public void removeCar(String carID) {
        cars.removeIf(car -> car.getCarID().equals(carID) && !car.isRentalStatus());
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isRentalStatus()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    // Renter Management
    public void addRenter(int renterType, String name, String email, String phoneNumber, String address) {
        switch(renterType) {
            case 1:
                renters.add(new RegularRenter(name, email, phoneNumber, address));
                break;
            case 2:
                renters.add(new FrequentRenter(name, email, phoneNumber, address));
                break;
            case 3:
                renters.add(new CorporateRenter(name, email, phoneNumber, address));
                break;
        }
    }

    public void displayRenters() {
        String leftAlignFormat = "| %-10s | %-15s | %-20s | %-15s | %-13s | %-10.2f |%n";
        System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
        System.out.format("| RenterID   | Name            | Email                | Phone Number    | Address       | Total Fee  |%n");
        System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
        for (Renter renter : renters) {
            System.out.format(leftAlignFormat, renter.getRenterID(), renter.getName(), renter.getEmail(), renter.getPhoneNumber(), renter.getAddress(), renter.getTotalRentalFee());
        }
        System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
    }

    public void displayRenterByID(String renterID) {
        Renter renter = findRenterByID(renterID);
        if (renter != null) {
            String leftAlignFormat = "| %-10s | %-15s | %-20s | %-15s | %-13s | %-10.2f |%n";
            System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
            System.out.format("| RenterID   | Name            | Email                | Phone Number    | Address       | Total Fee  |%n");
            System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
            System.out.format(leftAlignFormat, renter.getRenterID(), renter.getName(), renter.getEmail(), renter.getPhoneNumber(), renter.getAddress(), renter.getTotalRentalFee());
            System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
        } else {
            System.out.println("Renter with ID " + renterID + " not found.");
        }
    }

    public void displayRenterByName(String name) {
        String leftAlignFormat = "| %-10s | %-15s | %-20s | %-15s | %-13s | %-10.2f |%n";
        System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
        System.out.format("| RenterID   | Name            | Email                | Phone Number    | Address       | Total Fee  |%n");
        System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
        for (Renter renter : renters) {
            if (renter.getName().equalsIgnoreCase(name)) {
                System.out.format(leftAlignFormat, renter.getRenterID(), renter.getName(), renter.getEmail(), renter.getPhoneNumber(), renter.getAddress(), renter.getTotalRentalFee());
            }
        }
        System.out.format("+------------+-----------------+----------------------+-----------------+---------------+------------+%n");
    }

    public void removeRenter(String renterID) {
        renters.removeIf(renter -> renter.getRenterID().equals(renterID) && renter.getRentedCars().isEmpty());
    }

    public List<Renter> getRenters() {
        return new ArrayList<>(renters);
    }

    // Rent Transaction Management
    public void rentCar(String transactionID, String renterID, String carID, Date rentalDate, boolean insuranceAdded, double distance) {
        Renter renter = findRenterByID(renterID);
        Car car = findCarByID(carID);
        if (renter != null && car != null && !car.isRentalStatus()) {
            RentalTransaction transaction = new RentalTransaction(transactionID, renter, car, rentalDate, insuranceAdded);
            transaction.calculateTotalRentalCost(distance);
            transactions.add(transaction);
            car.setRentalStatus(true);
            renter.getRentedCars().add(car);
            System.out.println("Car rented successfully!");
        } else {
            System.out.println("Car rental failed. Either the car is already rented or the renter/car ID is invalid.");
        }
    }

    public void returnCar(String transactionID, Date returnDate) {
        RentalTransaction transaction = findTransactionByID(transactionID);
        if (transaction != null) {
            transaction.returnCar(returnDate);
            transaction.calculateDamageCost();
            System.out.println("Car returned successfully!");
            System.out.println(transaction);
        } else {
            System.out.println("Return failed. Invalid transaction ID.");
        }
    }

    public Renter findRenterByID(String renterID) {
        for (Renter renter : renters) {
            if (renter.getRenterID().equals(renterID)) {
                return renter;
            }
        }
        return null;
    }

    public Car findCarByID(String carID) {
        for (Car car : cars) {
            if (car.getCarID().equals(carID)) {
                return car;
            }
        }
        return null;
    }

    public RentalTransaction findTransactionByID(String transactionID) {
        for (RentalTransaction transaction : transactions) {
            if (transaction.getTransactionID().equals(transactionID)) {
                return transaction;
            }
        }
        return null;
    }
}