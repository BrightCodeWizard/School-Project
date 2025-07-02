package ems_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EmployeePanel extends JFrame {

    private JTextField nameField, emailField, ageField, departmentField, jobTitleField, phoneField;
    private DefaultTableModel tableModel;
    private JTable employeeTable;
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public EmployeePanel() {
        setTitle("ems_project.Employee Management");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel for form inputs
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));

        nameField = new JTextField();
        emailField = new JTextField();
        ageField = new JTextField();
        departmentField = new JTextField();
        jobTitleField = new JTextField();
        phoneField = new JTextField();

        formPanel.add(new JLabel("Name:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Email:")); formPanel.add(emailField);
        formPanel.add(new JLabel("Age:")); formPanel.add(ageField);
        formPanel.add(new JLabel("Department:")); formPanel.add(departmentField);
        formPanel.add(new JLabel("Job Title:")); formPanel.add(jobTitleField);
        formPanel.add(new JLabel("Phone:")); formPanel.add(phoneField);

        JButton addButton = new JButton("Add ems_project.Employee");
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        // Table to display employee data
        String[] columnNames = {"ID", "Name", "Email", "Age", "Department", "Job Title", "Phone"};
        tableModel = new DefaultTableModel(columnNames, 0);
        employeeTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listener
        addButton.addActionListener(e -> addEmployee());

        // Load existing employees
        loadEmployees();
    }

    private void addEmployee() {
        String name = nameField.getText();
        String email = emailField.getText();
        int age = Integer.parseInt(ageField.getText());
        String dept = departmentField.getText();
        String title = jobTitleField.getText();
        String phone = phoneField.getText();

        Employee emp = new Employee(0, name, email, age, dept, title, phone);
        employeeDAO.addEmployee(emp);
        loadEmployees();
        clearFields();
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        ageField.setText("");
        departmentField.setText("");
        jobTitleField.setText("");
        phoneField.setText("");
    }

    private void loadEmployees() {
        tableModel.setRowCount(0);  // Clear existing
        List<Employee> list = employeeDAO.getALLEmployees();
        for (Employee emp : list) {
            Object[] row = {
                    emp.getId(), emp.getName(), emp.getEmail(), emp.getAge(),
                    emp.getDepartment(), emp.getJobTitle(), emp.getPhone()
            };
            tableModel.addRow(row);
        }
    }

    // Run the panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EmployeePanel().setVisible(true);
        });
    }
}
