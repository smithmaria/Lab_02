package models;

import java.util.ArrayList;

/**
 * Demo program showing inheritance hierarchy with Worker and SalaryWorker
 */

public class InheritanceDemo {
    public static void main(String[] args) {
        // ArrayList to hold workers
        ArrayList<Worker> workers = new ArrayList<>();

        // Create 3 Workers
        workers.add(new Worker("11111", "John", "Smith", "Mr.", 1985, 15.50));
        workers.add(new Worker("W002", "Sarah", "Johnson", "Ms.", 1990, 18.75));
        workers.add(new Worker("W003", "Mike", "Brown", "", 1988, 22.00));

        // Create 3 SalaryWorkers
        workers.add(new SalaryWorker("S001", "Lisa", "Wilson", "Dr.", 1982, 0.0, 65000.0));
        workers.add(new SalaryWorker("S002", "David", "Garcia", "Mr.", 1979, 0.0, 72000.0));
        workers.add(new SalaryWorker("S003", "Emma", "Davis", "Ms.", 1986, 0.0, 58000.0));

        // Array of hours for each week
        double[] weeklyHours = {40.0, 50.0, 40.0};
        String[] weekNames = {"Week 1", "Week 2", "Week 3"};

        System.out.println("=".repeat(80));
        System.out.println("                    WEEKLY PAYROLL REPORT");
        System.out.println("=".repeat(80));

        // Loop through each week
        for (int week = 0; week < 3; week++) {
            System.out.println("\n" + "=".repeat(40));
            System.out.printf("              %s (%.1f hours)\n", weekNames[week], weeklyHours[week]);
            System.out.println("=".repeat(40));

            System.out.printf("%-20s %-15s %-10s%n", "Name", "Type", "Weekly Pay");
            System.out.println("-".repeat(45));

            // Display pay for each worker
            for (Worker worker : workers) {
                String name = worker.fullName();
                String type = (worker instanceof SalaryWorker) ? "Salary" : "Hourly";
                double pay = worker.calculateWeeklyPay(weeklyHours[week]);

                System.out.printf("%-20s %-15s $%8.2f%n", name, type, pay);
            }

            // Calculate and show totals
            double totalPay = 0;
            for (Worker worker : workers) {
                totalPay += worker.calculateWeeklyPay(weeklyHours[week]);
            }

            System.out.println("-".repeat(45));
            System.out.printf("%-35s $%8.2f%n", "TOTAL PAYROLL:", totalPay);
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    DETAILED PAY BREAKDOWN");
        System.out.println("=".repeat(80));

        // Show detailed breakdown for Week 2 (overtime week)
        System.out.println("\nDetailed breakdown for Week 2 (50 hours):");
        for (Worker worker : workers) {
            worker.displayWeeklyPay(50.0);
        }
    }
}
