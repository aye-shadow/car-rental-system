import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private CRMS crms;

    @BeforeEach
    public void setUp() {
        crms = new CRMS();
    }

    @Test
    public void testAddAndDisplayCars() {
        assertEquals(3, crms.getAvailableCars().size());
    }

    @Test
    public void testAddAndDisplayRenters() {
        assertEquals(3, crms.getRenters().size());
    }

    @Test
    public void testRentCar() {
//        crms.rentCar("R001", "C001", true, 100);
        RentalTransaction transaction = crms.findTransactionByID("T001");
        assertNotNull(transaction);
        assertEquals("T001", transaction.getTransactionID());
    }

    @Test
    public void testReturnCar() {
//        crms.rentCar("R001", "C001", true, 100);
//        crms.returnCar("T001");
        RentalTransaction transaction = crms.findTransactionByID("T001");
        assertTrue(transaction.isReturned());
    }

    @Test
    public void testRemoveCar() {
        crms.removeCar("C001");
        assertNull(crms.findCarByID("C001"));
    }

    @Test
    public void testRemoveRenter() {
        crms.removeRenter("R001");
        assertNull(crms.findRenterByID("R001"));
    }
}