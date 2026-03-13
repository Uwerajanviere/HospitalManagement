package com.hms.dao;

import com.hms.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentDAO {

    @Autowired
    private Connection connection;

    public void insertAppointment(Appointment appointment) throws SQLException {
        String query = "INSERT INTO appointments(doctor_id, patient_id, appointment_date, status) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, appointment.getDoctorId());
        ps.setInt(2, appointment.getPatientId());
        ps.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDate()));
        ps.setString(4, appointment.getStatus());
        ps.executeUpdate();
    }

    public void updateStatus(int appointmentId, String status) throws SQLException {
        String query = "UPDATE appointments SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, status);
        ps.setInt(2, appointmentId);
        ps.executeUpdate();
    }

    public List<String> getAppointmentsCountPerDoctor() throws SQLException {
        List<String> result = new ArrayList<>();
        String query = """
            SELECT d.first_name, d.last_name, COUNT(a.id) AS total_appointments
            FROM doctors d
            LEFT JOIN appointments a ON d.id = a.doctor_id
            GROUP BY d.id
        """;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            result.add(rs.getString("first_name") + " " + rs.getString("last_name") +
                    " - Appointments: " + rs.getInt("total_appointments"));
        }
        return result;
    }


    public List<String> getAppointmentsPerMonth() throws SQLException {
        List<String> result = new ArrayList<>();
        String query = """
            SELECT EXTRACT(MONTH FROM appointment_date) AS month, COUNT(*) AS total
            FROM appointments
            GROUP BY month
            ORDER BY month
        """;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            result.add("Month " + rs.getInt("month") + " - Total Appointments: " + rs.getInt("total"));
        }
        return result;
    }
}