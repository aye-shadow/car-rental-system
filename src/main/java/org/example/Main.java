package org.example;

import java.util.Scanner;

public class Main {
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
            System.out.println("6. Return a car");
            System.out.println("7. Display rental details");

            System.out.println("8. Remove a car");
            System.out.println("9. Remove a renter");

            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Display car by:\n 1. ID\n 2. Brand\n 3. Type\n 4. All\n 5. Available\nChoose an option: ");
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
                        case 5:
                            System.out.println("* Displaying available cars:");
                            crms.displayAvailableCars();
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }
                    break;
                case 2:
                    System.out.print("Display renter by:\n 1. ID\n 2. Name\n 3. All\nChoose an option: ");
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
                        String name = "";
                        boolean isEmpty, nameExists;
                        do {
                            name = scanner.nextLine();
                            isEmpty = name.isEmpty();
                            nameExists = crms.isRenterNameExists(name);
                            if (isEmpty) {
                                System.out.print("Name cannot be empty. Enter name: ");
                            }
                            else {
                                System.out.print("Name already exists. Enter name: ");
                            }
                        } while(isEmpty || nameExists);

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
                    System.out.print("Who's renting? Enter name: ");
                    String rentRenterName = scanner.nextLine();
                    if (crms.isRenterNameExists(rentRenterName)) {
                        crms.displayAvailableCars();
                        System.out.print("Enter car ID: ");
                        String rentCarId;
                        Car selectedCar;
                        do {
                            rentCarId = scanner.nextLine();
                            selectedCar = crms.findCarByID(rentCarId);
                            if (selectedCar == null) {
                                System.out.print("Car ID does not exist. Enter a valid car ID: ");
                            }
                        } while (selectedCar == null);
                        boolean addInsurance = false;
                        if (selectedCar.isInsurable()) {
                            System.out.print("Add insurance? (y/n): ");
                            String insuranceChoice = scanner.nextLine();
                            addInsurance = insuranceChoice.equalsIgnoreCase("yes");
                        }
                        crms.rentCar(rentRenterName, rentCarId, addInsurance);
                    }
                    else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                // In Main.java
                case 6:
                    System.out.print("Who's returning? Enter name: ");
                    String returnRenterName = scanner.nextLine();
                    if (crms.isRenterNameExists(returnRenterName)) {
                        crms.displayRenterCurrentRentals(returnRenterName);
                        System.out.print("Enter returning car ID: ");
                        String returnCarId = scanner.nextLine();
                        try {
                            crms.returnCar(returnRenterName, returnCarId);
                            System.out.println("Car returned successfully!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                case 7:
                    System.out.print("Display transactional: \n 1. History\n 2. Current\nChoose an option: ");
                    int displayTransType = scanner.nextInt();
                    switch(displayTransType) {
                        case 1:
                            System.out.print("\tDisplay transactions by:\n\t 1. Renter\n\t 2. All\n\tChoose an option: ");
                            int displayTransChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            switch (displayTransChoice) {
                                case 1:
                                    System.out.print("\tEnter renter's name: ");
                                    String renterName = scanner.nextLine();
                                    if (crms.isRenterNameExists(renterName)) {
                                        crms.displayTransactionsByRenter(renterName);
                                    }
                                    else {
                                        System.out.println("\tRenter does not exist. Please try again.");
                                    }
                                    break;

                                case 2:
                                    crms.displayAllTransactions();
                                    break;
                                default:
                                    System.out.println("\tInvalid option. Please try again.");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.print("\tDisplay current rentals by: \n\t 1. All\n\t 2. Renter\n\tChoose an option: ");
                            int displayCurrentChoice = scanner.nextInt();
                            switch (displayCurrentChoice) {
                                case 1:
                                    crms.displayAllCurrentRentals();
                                    break;
                                case 2:
                                    System.out.print("\tEnter renter's name: ");
                                    String renterName = scanner.nextLine();
                                    if (crms.isRenterNameExists(renterName)) {
                                        crms.displayRenterCurrentRentals(renterName);
                                    }
                                    else {
                                        System.out.println("\tRenter does not exist. Please try again.");
                                    }
                                    break;
                                default:
                                    System.out.println("\tInvalid option. Please try again.");
                                    break;
                            }
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                            break;
                    }

                    break;
                case 8:
                    System.out.print("Enter car ID: ");
                    String removeCarId = scanner.nextLine();
                    crms.removeCar(removeCarId);
                    break;
                case 9:
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