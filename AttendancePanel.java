package ems_project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class AttendancePanel extends JFrame {

    private JTextField empIdField, dateField;
    private JComboBox<String> statusBox;
    private DefaultTableModel tableModel;
    private AttendanceDAO attendanceDAO = new AttendanceDAO();

    public AttendancePanel() {
        setTitle("Attendance Tracker");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        empIdField = new JTextField();
        dateField = new JTextField(LocalDate.now().toString());
        statusBox = new JComboBox<>(new String[]{"Present", "Absent", "Leave"});

        panel.add(new JLabel("ems_project.Employee ID:")); panel.add(empIdField);
        panel.add(new JLabel("Date (YYYY-MM-DD):")); panel.add(dateField);
        panel.add(new JLabel("Status:")); panel.add(statusBox);

        JButton markBtn = new JButton("Mark Attendance");
        panel.add(markBtn);
        add(panel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ems_project.Employee ID", "Date", "Status"}, 0);
        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        markBtn.addActionListener(e -> markAttendance());
        loadAttendance();
    }

    private void markAttendance() {
        int empId = Integer.parseInt(empIdField.getText());
        String date = dateField.getText();
        String status = (String) statusBox.getSelectedItem();

        attendanceDAO.markAttendance(empId, date, status);
        loadAttendance();
        empIdField.setText("");
    }

    private void loadAttendance() {
        tableModel.setRowCount(0);
        List<String[]> records = attendanceDAO.getAllAttendance();
        for (String[] r : records) {
            tableModel.addRow(r);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AttendancePanel().setVisible(true);
        });
    }
}
