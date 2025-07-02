package ems_project;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("ems_project.Employee Management System - ems_project.Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel titleLabel = new JLabel("ems_project.Employee Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel);

        JButton employeeBtn = new JButton("Manage Employees");
        JButton payrollBtn = new JButton("Manage Payroll");
        JButton attendanceBtn = new JButton("Track Attendance");
        JButton exitBtn = new JButton("Exit");

        add(employeeBtn);
        add(payrollBtn);
        add(attendanceBtn);
        add(exitBtn);

        employeeBtn.addActionListener(e -> {
            new EmployeePanel().setVisible(true);
        });

        payrollBtn.addActionListener(e -> {
            new PayrollPanel().setVisible(true);
        });

        attendanceBtn.addActionListener(e -> {
            new AttendancePanel().setVisible(true);
        });

        exitBtn.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }
}
