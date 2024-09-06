package org.example;

import java.text.SimpleDateFormat;
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

    public void displayAllCars() {
        String leftAlignFormat = "| %-10s | %-11s | %-15s | %-10s | %-4d | %-10.2f | %-10s | %-8s | %-10s |%n";
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+------------+%n");
        System.out.format("| CarID      | PlateNumber | Brand           | Model      | Year | Rental Fee | Type       | Rented   | Insurable  |%n");
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+------------+%n");
        for (Car car : cars) {
            String carType = getCarType(car);
            String rentalStatus = car.isRentalStatus() ? "Yes" : "No";
            String insurableStatus = car.isInsurable() ? "Yes" : "No";
            System.out.format(leftAlignFormat, car.getCarID(), car.getPlateNumber(), car.getBrand(), car.getModel(), car.getYear(), car.getRentalFee(), carType, rentalStatus, insurableStatus);
        }
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+----------+------------+%n");
    }

    public void displayAvailableCars() {
        String leftAlignFormat = "| %-10s | %-11s | %-15s | %-10s | %-4d | %-10.2f | %-10s | %-10s |%n";
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+------------+%n");
        System.out.format("| CarID      | PlateNumber | Brand           | Model      | Year | Rental Fee | Type       | Insurable  |%n");
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+------------+%n");
        for (Car car : cars) {
            if (!car.isRentalStatus()) {
                String carType = getCarType(car);
                String insurableStatus = car.isInsurable() ? "Yes" : "No";
                System.out.format(leftAlignFormat, car.getCarID(), car.getPlateNumber(), car.getBrand(), car.getModel(), car.getYear(), car.getRentalFee(), carType, insurableStatus);
            }
        }
        System.out.format("+------------+-------------+-----------------+------------+------+------------+------------+------------+%n");
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
    public void rentCar(String renterName, String carID, boolean addInsurance) {
        Renter renter = findRenterByName(renterName);
        Car car = findCarByID(carID);
        if (renter != null && car != null && !car.isRentalStatus()) {
            car.setRentalStatus(true);
            renter.getRentedCars().add(car);
            RentalTransaction transaction = new RentalTransaction(renter, car, addInsurance);
            transactions.add(transaction);
            System.out.println("Car rented successfully!");
            if (addInsurance) {
                System.out.println("Insurance added.");
            }
        } else {
            System.out.println("Car rental failed. Either the car is already rented or the renter/car ID is invalid.");
        }
    }

    public RentalTransaction findTransactionByRenterAndCar(Renter renter, Car car) {
        for (RentalTransaction transaction : transactions) {
            if (transaction.getRenter().equals(renter) && transaction.getCar().equals(car) && !transaction.isReturned()) {
                return transaction;
            }
        }
        return null;
    }

    public void returnCar(String renterName, String carID) throws Exception {
        Renter renter = findRenterByName(renterName);
        if (renter != null) {
            Car carToReturn = null;
            for (Car car : renter.getRentedCars()) {
                if (car.getCarID().equals(carID)) {
                    carToReturn = car;
                    break;
                }
            }
            if (carToReturn != null) {
                renter.getRentedCars().remove(carToReturn);
                carToReturn.setRentalStatus(false);
                RentalTransaction transaction = findTransactionByRenterAndCar(renter, carToReturn);
                if (transaction != null) {
                    transaction.returnCar(new Date());
                } else {
                    throw new Exception("Transaction not found for the car.");
                }
            } else {
                throw new Exception("Car ID not found in renter's rented cars.");
            }
        } else {
            throw new Exception("Renter not found.");
        }
    }

    public void displayAllTransactions() {
        if (!transactions.isEmpty()) {
            String leftAlignFormat = "| %-15s | %-10s | %-20s | %-25s | %-10s |%n";
            System.out.format("+-----------------+------------+----------------------+---------------------------+------------+%n");
            System.out.format("| Renter Name     | Car ID     | Car Brand/Model      | Rental Date/Time          | Insured    |%n");
            System.out.format("+-----------------+------------+----------------------+---------------------------+------------+%n");
            for (RentalTransaction transaction : transactions) {
                String renterName = transaction.getRenter().getName();
                String carID = transaction.getCar().getCarID();
                String carBrandModel = transaction.getCar().getBrand() + " " + transaction.getCar().getModel();
                String rentalDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getRentalDate());
                String insured = transaction.isInsuranceAdded() ? "Yes" : "No";
                System.out.format(leftAlignFormat, renterName, carID, carBrandModel, rentalDateTime, insured);
            }
            System.out.format("+-----------------+------------+----------------------+---------------------------+------------+%n");
        } else {
            System.out.println("No transactions found.");
        }
    }

    public void displayTransactionsByRenter(String renterName) {
        List<RentalTransaction> renterTransactions = new ArrayList<>();
        for (RentalTransaction transaction : transactions) {
            if (transaction.getRenter().getName().equalsIgnoreCase(renterName)) {
                renterTransactions.add(transaction);
            }
        }

        if (!renterTransactions.isEmpty()) {
            String leftAlignFormat = "| %-15s | %-10s | %-20s | %-25s | %-10s |%n";
            System.out.format("+-----------------+------------+----------------------+---------------------------+------------+%n");
            System.out.format("| Renter Name     | Car ID     | Car Brand/Model      | Rental Date/Time          | Insured    |%n");
            System.out.format("+-----------------+------------+----------------------+---------------------------+------------+%n");
            for (RentalTransaction transaction : renterTransactions) {
                String carID = transaction.getCar().getCarID();
                String carBrandModel = transaction.getCar().getBrand() + " " + transaction.getCar().getModel();
                String rentalDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getRentalDate());
                String insured = transaction.isInsuranceAdded() ? "Yes" : "No";
                System.out.format(leftAlignFormat, renterName, carID, carBrandModel, rentalDateTime, insured);
            }
            System.out.format("+-----------------+------------+----------------------+---------------------------+------------+%n");
        } else {
            System.out.println("No transactions found for renter: " + renterName);
        }
    }

    public void displayAllCurrentRentals() {
        String leftAlignFormat = "| %-15s | %-10s | %-20s | %-25s |%n";
        System.out.format("+-----------------+------------+----------------------+%n");
        System.out.format("| Renter Name     | Car ID     | Car Brand/Model      |%n");
        System.out.format("+-----------------+------------+----------------------+%n");
        for (Renter renter : renters) {
            for (Car car : renter.getRentedCars()) {
                String carID = car.getCarID();
                String carBrandModel = car.getBrand() + " " + car.getModel();
                System.out.format(leftAlignFormat, renter.getName(), carID, carBrandModel);
            }
        }
        System.out.format("+-----------------+------------+----------------------+%n");
    }

    public void displayRenterCurrentRentals(String renterName) {
        Renter renter = findRenterByName(renterName);
        if (renter != null) {
            List<Car> rentedCars = renter.getRentedCars();
            if (!rentedCars.isEmpty()) {
                String leftAlignFormat = "| %-15s | %-10s | %-20s | %-25s |%n";
                System.out.format("+-----------------+------------+----------------------+%n");
                System.out.format("| Renter Name     | Car ID     | Car Brand/Model      |%n");
                System.out.format("+-----------------+------------+----------------------+%n");
                for (Car car : rentedCars) {
                    String carID = car.getCarID();
                    String carBrandModel = car.getBrand() + " " + car.getModel();
                    System.out.format(leftAlignFormat, renterName, carID, carBrandModel);
                }
                System.out.format("+-----------------+------------+----------------------+%n");
            } else {
                System.out.println("No current transactions found for renter: " + renterName);
            }
        } else {
            System.out.println("Renter not found: " + renterName);
        }
    }

    public Renter findRenterByName(String name) {
        for (Renter renter : renters) {
            if (renter.getName().equalsIgnoreCase(name)) {
                return renter;
            }
        }
        return null;
    }

    public Renter findRenterByID(String renterID) {
        for (Renter renter : renters) {
            if (renter.getRenterID().equals(renterID)) {
                return renter;
            }
        }
        return null;
    }

    public boolean isRenterNameExists(String name) {
        for (Renter renter : renters) {
            if (renter.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Car findCarByID(String carID) {
        for (Car car : cars) {
            if (car.getCarID().equals(carID)) {
                return car;
            }
        }
        return null;
    }

    public boolean isCarIDExists(String carID) {
        for (Car car : cars) {
            if (car.getCarID().equalsIgnoreCase(carID)) {
                return true;
            }
        }
        return false;
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