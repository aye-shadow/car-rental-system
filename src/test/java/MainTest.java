import org.example.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private CRMS crms;

    @BeforeEach
    void setUp() {
        crms = new CRMS(false);
    }

    @Test
    void testAddCar() {
        crms.addCar(1, "Toyota", "Corolla", 2020, 50.0);
        assertTrue(crms.isCarIDExists(crms.getAvailableCars().get(0).getCarID()));
    }

    @Test
    void testAddRenter() {
        crms.addRenter(1, "John Doe", "john@example.com", "1234567890", "123 Main St");
        assertTrue(crms.isRenterNameExists("John Doe"));
    }

    @Test
    void testRentCar() {
        crms.addCar(1, "Toyota", "Corolla", 2020, 50.0);
        crms.addRenter(1, "John Doe", "john@example.com", "1234567890", "123 Main St");
        String carID = crms.getAvailableCars().get(0).getCarID();
        crms.rentCar("John Doe", carID, true);
        assertTrue(crms.findCarByID(carID).isRentalStatus());
    }

    @Test
    void testReturnCar() throws Exception {
        crms.addCar(1, "Toyota", "Corolla", 2020, 50.0);
        crms.addRenter(1, "John Doe", "john@example.com", "1234567890", "123 Main St");
        String carID = crms.getAvailableCars().get(0).getCarID();
        crms.rentCar("John Doe", carID, true);
        crms.returnCar("John Doe", carID);
        assertFalse(crms.findCarByID(carID).isRentalStatus());
    }

    @Test
    void testFindRenterByName() {
        crms.addRenter(1, "John Doe", "john@example.com", "1234567890", "123 Main St");
        assertNotNull(crms.findRenterByName("John Doe"));
    }

    @Test
    void testFindCarByID() {
        crms.addCar(1, "Toyota", "Corolla", 2020, 50.0);
        String carID = crms.getAvailableCars().get(0).getCarID();
        assertNotNull(crms.findCarByID(carID));
    }

    @Test
    void testFindTransactionByID() {
        crms.addCar(1, "Toyota", "Corolla", 2020, 50.0);
        crms.addRenter(1, "John Doe", "john@example.com", "1234567890", "123 Main St");
        String carID = crms.getAvailableCars().get(0).getCarID();
        crms.rentCar("John Doe", carID, true);
        RentalTransaction transaction = crms.findTransactionByRenterAndCar(crms.findRenterByName("John Doe"), crms.findCarByID(carID));
        assertNotNull(crms.findTransactionByID(transaction.getTransactionID()));
    }
}