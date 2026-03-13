package com.hms.dao;

import com.hms.model.MedicalRecord;
import com.hms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDAO {

    @Autowired
    private Connection connection;

    // Insert new patient
    public void insertPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO patients(first_name, last_name, date_of_birth, gender, phone_number, email) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, patient.getFirstName());
        ps.setString(2, patient.getLastName());
        ps.setDate(3, Date.valueOf(patient.getDateOfBirth()));
        ps.setString(4, patient.getGender());
        ps.setString(5, patient.getPhoneNumber());
        ps.setString(6, patient.getEmail());
        ps.executeUpdate();
    }

    // Retrieve all patients
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Patient p = new Patient();
            p.setId(rs.getInt("id"));
            p.setFirstName(rs.getString("first_name"));
            p.setLastName(rs.getString("last_name"));
            p.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            p.setGender(rs.getString("gender"));
            p.setPhoneNumber(rs.getString("phone_number"));
            p.setEmail(rs.getString("email"));
            p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            patients.add(p);
        }
        return patients;
    }

    // Retrieve all patients for a specific doctor
    public List<Patient> getPatientsByDoctor(int doctorId) throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = """
            SELECT p.*
            FROM patients p
            JOIN appointments a ON p.id = a.patient_id
            WHERE a.doctor_id = ?
        """;
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, doctorId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Patient p = new Patient();
            p.setId(rs.getInt("id"));
            p.setFirstName(rs.getString("first_name"));
            p.setLastName(rs.getString("last_name"));
            p.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
            p.setGender(rs.getString("gender"));
            p.setPhoneNumber(rs.getString("phone_number"));
            p.setEmail(rs.getString("email"));
            p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            patients.add(p);
        }
        return patients;
    }

    // Delete patient by ID (cascades appointments/medical records)
    public void deletePatient(int patientId) throws SQLException {
        String query = "DELETE FROM patients WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, patientId);
        ps.executeUpdate();
    }
}