package models;

/**
 * SalaryWorker class extends Worker to represent a salaried employee
 * @author Maria Smith
 */
public class SalaryWorker extends Worker {
    private double annualSalary;

    /**
     * Constructor for SalaryWorker
     * @param ID unique identifier
     * @param firstName first name
     * @param lastName last name
     * @param title title (Mr., Mrs., etc.)
     * @param YOB year of birth
     * @param hourlyPayRate hourly rate (retained for polymorphism)
     * @param annualSalary annual salary amount
     */
    public SalaryWorker(String ID, String firstName, String lastName, String title, int YOB, double hourlyPayRate, double annualSalary) {
        super(ID, firstName, lastName, title, YOB, hourlyPayRate);
        this.annualSalary = annualSalary;
    }

    /**
     * Gets annual salary
     * @return annual salary
     */
    public double getAnnualSalary() {
        return annualSalary;
    }

    /**
     * Sets annual salary
     * @param annualSalary new annual salary
     */
    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    /**
     * Override calculateWeeklyPay - hours parameter ignored for salaried employees
     * @param hoursWorked ignored for salary calculation
     * @return weekly pay (annual salary / 52)
     */
    @Override
    public double calculateWeeklyPay(double hoursWorked) {
        return annualSalary / 52.0;
    }

    /**
     * Override displayWeeklyPay for salary display
     * @param hoursWorked ignored for salary display
     */
    @Override
    public void displayWeeklyPay(double hoursWorked) {
        double weeklyPay = calculateWeeklyPay(hoursWorked);
        System.out.println("\n=== Weekly Pay Report for " + fullName() + " ===");
        System.out.printf("Annual Salary: $%.2f%n", annualSalary);
        System.out.printf("Weekly Pay: $%.2f (Annual Salary / 52 weeks)%n", weeklyPay);
        System.out.println("Hours worked does not affect salary pay.");
    }

    /**
     * Override toCSV to include annual salary
     * @return CSV string with all Worker data plus annual salary
     */
    @Override
    public String toCSV() {
        return super.toCSV() + ", " + annualSalary;
    }

    /**
     * Override toXML to include annual salary
     * @return XML string with all Worker data plus annual salary
     */
    @Override
    public String toXML() {
        String workerXML = super.toXML();
        String result = workerXML.substring(0, workerXML.lastIndexOf("</Person>"));
        result += "\t<AnnualSalary>" + annualSalary + "</AnnualSalary>\n</Person>";
        return result;
    }

    /**
     * Override toJSON to include annual salary
     * @return JSON string with all Worker data plus annual salary
     */
    @Override
    public String toJSON() {
        String workerJSON = super.toJSON();
        String result = workerJSON.substring(0, workerJSON.lastIndexOf("}"));
        result += ",\n\t\"AnnualSalary\": " + annualSalary + "\n}";
        return result;
    }

    /**
     * Override toString to include annual salary
     * @return formatted string with all Worker data plus annual salary
     */
    @Override
    public String toString() {
        String workerString = super.toString();
        String result = workerString.substring(0, workerString.lastIndexOf("}"));
        result += String.format(", Annual Salary='$%.2f'}", annualSalary);
        return result;
    }
}
