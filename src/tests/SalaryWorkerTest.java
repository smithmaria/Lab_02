package tests;

import models.SalaryWorker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SalaryWorkerTest {

    private SalaryWorker salaryWorker;

    @BeforeEach
    void setUp() {
        salaryWorker = new SalaryWorker("11111", "Jane", "Doe", "Ms.", 1980, 0.0, 52000.0);
    }

    @Test
    void testConstructor() {
        assertEquals("11111", salaryWorker.getID());
        assertEquals("Jane", salaryWorker.getFirstName());
        assertEquals("Doe", salaryWorker.getLastName());
        assertEquals("Ms.", salaryWorker.getTitle());
        assertEquals(1980, salaryWorker.getYOB());
        assertEquals(52000.0, salaryWorker.getAnnualSalary());
    }

    @Test
    void testCalculateWeeklyPay() {
        double pay40 = salaryWorker.calculateWeeklyPay(40);
        double pay50 = salaryWorker.calculateWeeklyPay(50);
        double pay30 = salaryWorker.calculateWeeklyPay(30);

        assertEquals(1000.0, pay40, 0.01);
        assertEquals(1000.0, pay50, 0.01);
        assertEquals(1000.0, pay30, 0.01);
    }

    @Test
    void testSetAnnualSalary() {
        salaryWorker.setAnnualSalary(60000.0);
        assertEquals(60000.0, salaryWorker.getAnnualSalary());
        assertEquals(1153.85, salaryWorker.calculateWeeklyPay(40), 0.01);
    }

    @Test
    void testToCSV() {
        String csv = salaryWorker.toCSV();
        assertTrue(csv.contains("11111"));
        assertTrue(csv.contains("Jane"));
        assertTrue(csv.contains("52000.0"));
    }

    @Test
    void testToJSON() {
        String json = salaryWorker.toJSON();
        assertTrue(json.contains("AnnualSalary"));
        assertTrue(json.contains("52000.0"));
    }

    @Test
    void testToXML() {
        String xml = salaryWorker.toXML();
        assertTrue(xml.contains("<AnnualSalary>52000.0</AnnualSalary>"));
    }

    @Test
    void testToString() {
        String str = salaryWorker.toString();
        assertTrue(str.contains("Jane"));
        assertTrue(str.contains("52000.0"));
    }
}
