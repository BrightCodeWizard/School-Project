package ems_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {

    public void savePayroll(PayrollManager payroll) {
        String sql = "INSERT INTO payroll (employee_id, base_salary, benefits, deductions, total_salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, payroll.getEmployeeId());
            stmt.setDouble(2, payroll.getBaseSalary());
            stmt.setDouble(3, payroll.getBenefits());
            stmt.setDouble(4, payroll.getDeductions());
            stmt.setDouble(5, payroll.calculateTotalSalary());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getAllPayrolls() {
        List<String[]> payrollList = new ArrayList<>();
        String sql = "SELECT * FROM payroll";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String[] data = {
                        String.valueOf(rs.getInt("employee_id")),
                        String.valueOf(rs.getDouble("base_salary")),
                        String.valueOf(rs.getDouble("benefits")),
                        String.valueOf(rs.getDouble("deductions")),
                        String.valueOf(rs.getDouble("total_salary"))
                };
                payrollList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payrollList;
    }
}
