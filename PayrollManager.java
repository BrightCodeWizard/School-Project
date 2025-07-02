package ems_project;

public class PayrollManager {
    private int employeeId;
    private double baseSalary;
    private double benefits;
    private double deductions;

    public PayrollManager(int employeeId, double baseSalary, double benefits, double deductions) {
        this.employeeId = employeeId;
        this.baseSalary = baseSalary;
        this.benefits = benefits;
        this.deductions = deductions;
    }

    public double calculateTotalSalary() {
        return baseSalary + benefits - deductions;
    }

    public int getEmployeeId() { return employeeId; }
    public double getBaseSalary() { return baseSalary; }
    public double getBenefits() { return benefits; }
    public double getDeductions() { return deductions; }
}
