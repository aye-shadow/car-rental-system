package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        CRMS crms = new CRMS();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Display cars:");
            System.out.println("2. Display renters");
            System.out.println("3. Add a car");
            System.out.println("4. Add a renter");

            System.out.println("5. Rent a car");
            System.out.println("6. Display rental details");

            System.out.println("7. Return a car");
            System.out.println("8. Remove a car");
            System.out.println("9. Remove a renter");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Display car by:\n 1. ID\n 2. Brand\n 3. Type\n4. All\nChoose an option: ");
                    int displayChoice = scanner.nextInt();
                    switch (displayChoice) {
                        case 1:
                            System.out.print("Enter car ID: ");
                            String carId = scanner.nextLine();
                            crms.displayCarByID(carId);
                            break;
                        case 2:
                            System.out.print("Enter car brand: ");
                            String carBrand = scanner.nextLine();
                            crms.displayCarByBrand(carBrand);
                            break;
                        case 3:
                            System.out.print("Enter car type: ");
                            String carType = scanner.nextLine();
                            crms.displayCarByType(carType);
                            break;
                        case 4:
                            System.out.println("* Displaying all cars:");
                            crms.displayAllCars();
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    break;
                case 2:
                    System.out.print("Display renter by:\n 1. ID\n 2. name\n 3. All\nChoose an option: ");
                    int renterChoice = scanner.nextInt();
                    switch (renterChoice) {
                        case 1:
                            System.out.print("Enter renter ID: ");
                            String renterId = scanner.nextLine();
                            crms.displayRenterByID(renterId);
                            break;
                        case 2:
                            System.out.print("Enter renter name: ");
                            String renterName = scanner.nextLine();
                            crms.displayRenterByName(renterName);
                            break;
                        case 3:
                            System.out.println("\n* Displaying all renters:");
                            crms.displayRenters();
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    break;
                case 3:
                    System.out.print("Choose car type:\n 1. Compact\n 2. SUV\n 3. Luxury\nChoose an option: ");
                    int addCarChoice = scanner.nextInt();
                    String carBrand = crms.chooseCarBrands(addCarChoice);
                    if (!carBrand.isEmpty()) {
                        String carModel = crms.chooseCarModels();
                        System.out.print("\tEnter year: ");
                        int carYear = scanner.nextInt();
                        System.out.print("\tEnter rental fee: ");
                        double rentalFee = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        crms.addCar(addCarChoice, carBrand, carModel, carYear, rentalFee);
                    }
                    break;
                case 4:
                    System.out.print("Choose renter type:\n 1. Regular\n 2. Frequent\n 3. Corporate\nChoose an option: ");
                    int addRenterChoice = scanner.nextInt();
                    if (addRenterChoice >= 1 && addRenterChoice <= 3) {
                        System.out.print("\tEnter name: ");
                        String name = scanner.nextLine();
                        while (name.isEmpty()) {
                            System.out.print("Name cannot be empty. Enter name: ");
                            name = scanner.nextLine();
                        }

                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();
                        while (email.isEmpty()) {
                            System.out.print("Email cannot be empty. Enter email: ");
                            email = scanner.nextLine();
                        }

                        System.out.print("Enter address: ");
                        String address = scanner.nextLine();
                        while (address.isEmpty()) {
                            System.out.print("Address cannot be empty. Enter address: ");
                            address = scanner.nextLine();
                        }

                        System.out.print("Enter phone number: ");
                        String phoneNumber = scanner.nextLine();
                        while (phoneNumber.isEmpty()) {
                            System.out.print("Phone number cannot be empty. Enter phone number: ");
                            phoneNumber = scanner.nextLine();
                        }

                        crms.addRenter(addRenterChoice, name, email, address, phoneNumber);
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 5:
                    System.out.print("Enter transaction ID: ");
                    String transactionId = scanner.nextLine();
                    System.out.print("Enter renter ID: ");
                    String rentRenterId = scanner.nextLine();
                    System.out.print("Enter car ID: ");
                    String rentCarId = scanner.nextLine();
                    System.out.print("Enter rental date (yyyy-mm-dd): ");
                    String rentDateStr = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date rentDate = null;
                    try {
                        rentDate = dateFormat.parse(rentDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                        logger.log(Level.SEVERE, "Invalid date format. Please use yyyy-mm-dd.", e);
                    }
                    System.out.print("Is the car insured (true/false): ");
                    boolean isInsured = scanner.nextBoolean();
                    System.out.print("Enter rental fee: ");
                    double rentalFee = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    crms.rentCar(transactionId, rentRenterId, rentCarId, rentDate, isInsured, rentalFee);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    System.out.print("Enter transaction ID: ");
                    String transId = scanner.nextLine();
                    RentalTransaction transaction = crms.findTransactionByID(transId);
                    if (transaction != null) {
                        System.out.println(transaction);
                    } else {
                        System.out.println("Transaction not found.");
                    }
                    break;
                case 10:
                    System.out.print("Enter transaction ID: ");
                    String returnTransId = scanner.nextLine();
                    System.out.print("Enter return date (yyyy-mm-dd): ");
                    String returnDateStr = scanner.nextLine();
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date returnDate = null;
                    try {
                        returnDate = dateFormat2.parse(returnDateStr);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use yyyy-mm-dd.");
                        logger.log(Level.SEVERE, "Invalid date format. Please use yyyy-mm-dd.", e);
                    }
                    crms.returnCar(returnTransId, returnDate);
                    break;
                case 11:
                    System.out.print("Enter car ID: ");
                    String removeCarId = scanner.nextLine();
                    crms.removeCar(removeCarId);
                    break;
                case 12:
                    System.out.print("Enter renter ID: ");
                    String removeRenterId = scanner.nextLine();
                    crms.removeRenter(removeRenterId);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}