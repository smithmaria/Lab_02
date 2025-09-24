/**
 * Worker class extends Person to represent a worker with hourly pay
 * Inherits all Person fields and methods, adds hourly pay functionality
 * @author Maria Smith
 */

public class Worker extends Person {
    private double hourlyPayRate;

    /**
     * Constructor for Worker class
     * Uses super() to call Person constructor, then sets hourly pay rate
     * @param ID unique identifier (passed to Person)
     * @param firstName first name (passed to Person)
     * @param lastName last name (passed to Person)
     * @param title person's title like Sr., Jr. (passed to Person)
     * @param YOB year of birth (passed to Person)
     * @param hourlyPayRate worker's hourly pay rate
     */
    public Worker(String ID, String firstName, String lastName, String title, int YOB, double hourlyPayRate) {
        super(ID, firstName, lastName, title, YOB);
        this.hourlyPayRate = hourlyPayRate;
    }

    /**
     * Constructor for worker class without title
     * Uses super() to call Person constructor, then sets hourly pay rate
     * @param ID unique identifier (passed to Person)
     * @param firstName first name (passed to Person)
     * @param lastName last name (passed to Person)
     * @param YOB year of birth (passed to Person)
     * @param hourlyPayRate worker's hourly pay rate
     */
    public Worker (String ID, String firstName, String lastName, int YOB, double hourlyPayRate) {
        super(ID, firstName, lastName, YOB);
        this.hourlyPayRate = hourlyPayRate;
    }

    /**
     * Gets hourly pay rate
     * @return hourly pay rate as double
     */
    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    /**
     * Sets the hourly pay rate
     * @param hourlyPayRate - new hourly pay rate
     */
    public void setHourlyPayRate(double hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    /**
     * Calculates weekly pay based on hours worked
     * Hours <= 40: pay at regular hourly rate
     * Hours > 40: first 40 hours at regular rate, remaining hours at 1.5x rate for overtime
     * @param hoursWorked - total hours worked in week
     * @return total weekly pay
     */
    public double calculateWeeklyPay(double hoursWorked) {
        if (hoursWorked <= 40) {
            return hourlyPayRate * hoursWorked;
        } else {
            double regularPay =  hourlyPayRate * 40;
            double overtimePay = (hourlyPayRate * 1.5) * (hoursWorked - 40);
            return regularPay + overtimePay;
        }
    }

    /**
     * Display weekly pay information
     * Shows regular hours/pay, overtime hours/pay, and total pay
     * @param hoursWorked - total hours worked in the week
     */
    public void displayWeeklyPay(double hoursWorked) {
        System.out.println("\n=== Weekly Pay Report for " + fullName() + " ===");

        if (hoursWorked <= 40) {
            // No overtime
            double totalPay = calculateWeeklyPay(hoursWorked);
            System.out.printf("Regular Hours: %.1f @ $%.2f/hr = $%.2f%n",
                    hoursWorked, hourlyPayRate, totalPay);
            System.out.printf("Overtime Hours: 0.0 @ $%.2f/hr = $0.00%n",
                    hourlyPayRate * 1.5);
            System.out.printf("Total Pay: $%.2f%n", totalPay);
        } else {
            // Has overtime
            double regularHours = 40.0;
            double overtimeHours = hoursWorked - 40;
            double regularPay = regularHours * hourlyPayRate;
            double overtimePay = overtimeHours * (hourlyPayRate * 1.5);
            double totalPay = regularPay + overtimePay;

            System.out.printf("Regular Hours: %.1f @ $%.2f/hr = $%.2f%n",
                    regularHours, hourlyPayRate, regularPay);
            System.out.printf("Overtime Hours: %.1f @ $%.2f/hr = $%.2f%n",
                    overtimeHours, hourlyPayRate * 1.5, overtimePay);
            System.out.printf("Total Pay: $%.2f%n", totalPay);
        }
    }

    /**
     * Override toCSV to include hourly pay rate
     * @return CSV formatted string with all Person data plus hourly pay rate
     */
    @Override
    public String toCSV() {
        return super.toCSV() + ", " + hourlyPayRate;
    }

    /**
     * Override to to XML to include hourly pay rate
     * @return XML formatted string with all Person data plus hourly rate
     */
    @Override
    public String toXML() {
        String personXML = super.toXML();
        String result = personXML.substring(0, personXML.lastIndexOf("</Person>"));
        result += "\t<HourlyPayRate>" + hourlyPayRate + "</HourlyPayRate>\n</Person>";
        return result;
    }

    /**
     * Override toJSON to include hourly pay rate
     * @return JSON formatted string with all Person data plus hourly pay rate
     */
    @Override
    public String toJSON() {
        String personJSON = super.toJSON();
        String result = personJSON.substring(0, personJSON.lastIndexOf("}"));
        result += "\"HourlyPayRate\":" + hourlyPayRate + "\n}";
        return result;
    }

    /**
     * Override toString to include hourly pay rate information
     * @return formatted string representation of Worker
     */
    @Override
    public String toString() {
        String personString = super.toString();
        String result = personString.substring(0, personString.lastIndexOf("}"));
        result += String.format(", Hourly Pay Rate='$%.2f'}", hourlyPayRate);
        return result;
    }
}
