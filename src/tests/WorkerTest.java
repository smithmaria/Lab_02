package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import models.Worker;

/**
 * Junit test class for models.Worker class
 * Tests constructors, setter methods, and additional specified methods
 *
 * @author Maria Smith
 */

public class WorkerTest {
    private Worker worker;

    /**
     * Set up test objects
     * Runs before every @Test method
     */
    @BeforeEach
    void setUp() {
        worker = new Worker("11111", "Test", "Name", "Dr.", 2000, 20.00);
    }

    /**
     * Test the main constructor with all parameters
     */
    @Test
    void testConstructor() {
        Assertions.assertEquals("11111", worker.getID());
        Assertions.assertEquals("Test", worker.getFirstName());
        Assertions.assertEquals("Name", worker.getLastName());
        Assertions.assertEquals("Dr.", worker.getTitle());
        Assertions.assertEquals(2000, worker.getYOB());
        Assertions.assertEquals(20.00, worker.getHourlyPayRate());
    }

    /**
     * Test calculateWeeklyPay with full 40 hours and under 40 hours
     */
    @Test
    void testCalculateWeeklyPayRegularHours() {
        double pay = worker.calculateWeeklyPay(40);
        assertEquals(800, pay, 0.01);

        double pay30 = worker.calculateWeeklyPay(30);
        assertEquals(600, pay30, 0.01);
    }

    /**
     * Test calculateWeeklyPay with overtime hours
     */
    @Test
    void testCalculateWeeklyPayOvertime() {
        double pay = worker.calculateWeeklyPay(50);
        assertEquals(1100, pay, 0.01);
    }

    /**
     * Test setHourlyPayRate
     */
    @Test
    void testSetHourlyPayRate() {
        worker.setHourlyPayRate(25.50);
        Assertions.assertEquals(25.50, worker.getHourlyPayRate(), 0.01);
    }

    /**
     * Test override toCSV
     */
    @Test
    void testToCSV() {
        String csv = worker.toCSV();
        assertTrue(csv.contains("11111"));
        assertTrue(csv.contains("Test"));
        assertTrue(csv.contains("Name"));
        assertTrue(csv.contains("20.0"));
    }

    /**
     * Test override toJSON
     */
    @Test
    void testToJSON() {
        String json = worker.toJSON();
        assertTrue(json.contains("HourlyPayRate"));
        assertTrue(json.contains("20.0"));
    }

    /**
     * Test override toXML
     */
    @Test
    void testToXML() {
        String xml = worker.toXML();
        assertTrue(xml.contains("<HourlyPayRate>20.0</HourlyPayRate>"));
    }

    /**
     * Test override toString
     */
    @Test
    void testToString() {
        String str = worker.toString();
        assertTrue(str.contains("11111"));
        assertTrue(str.contains("Test"));
        assertTrue(str.contains("20.0"));
    }
}
