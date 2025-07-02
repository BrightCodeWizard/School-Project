package ems_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public void markAttendance(int empId, String date, String status) {
        String sql = "INSERT INTO attendance (employee_id, date, status) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setString(3, status);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getAllAttendance() {
        List<String[]> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String[] data = {
                        String.valueOf(rs.getInt("employee_id")),
                        rs.getDate("date").toString(),
                        rs.getString("status")
                };
                list.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
