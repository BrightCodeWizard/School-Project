package ems_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PayrollPanel extends JFrame {

    private JTextField empIdField, baseField, benefitsField, deductionsField;
    private JTable payrollTable;
    private DefaultTableModel model;

    private PayrollDAO payrollDAO = new PayrollDAO();

    public PayrollPanel() {
        setTitle("Payroll Management");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        empIdField = new JTextField();
        baseField = new JTextField();
        benefitsField = new JTextField();
        deductionsField = new JTextField();

        inputPanel.add(new JLabel("ems_project.Employee ID:"));
        inputPanel.add(empIdField);
        inputPanel.add(new JLabel("Base Salary:"));
        inputPanel.add(baseField);
        inputPanel.add(new JLabel("Benefits:"));
        inputPanel.add(benefitsField);
        inputPanel.add(new JLabel("Deductions:"));
        inputPanel.add(deductionsField);

        JButton saveBtn = new JButton("Save Payroll");
        inputPanel.add(saveBtn);

        add(inputPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Emp ID", "Base", "Benefits", "Deductions", "Total"}, 0);
        payrollTable = new JTable(model);
        add(new JScrollPane(payrollTable), BorderLayout.CENTER);

        saveBtn.addActionListener(e -> savePayroll());
        loadPayrolls();
    }

    private void savePayroll() {
        int empId = Integer.parseInt(empIdField.getText());
        double base = Double.parseDouble(baseField.getText());
        double benefits = Double.parseDouble(benefitsField.getText());
        double deductions = Double.parseDouble(deductionsField.getText());

        PayrollManager p = new PayrollManager(empId, base, benefits, deductions);
        payrollDAO.savePayroll(p);
        loadPayrolls();
        clearFields();
    }

    private void clearFields() {
        empIdField.setText("");
        baseField.setText("");
        benefitsField.setText("");
        deductionsField.setText("");
    }

    private void loadPayrolls() {
        model.setRowCount(0);
        List<String[]> records = payrollDAO.getAllPayrolls();
        for (String[] r : records) {
            model.addRow(r);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PayrollPanel().setVisible(true);
        });
    }
}
