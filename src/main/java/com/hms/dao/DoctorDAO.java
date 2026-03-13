package com.hms.dao;

import com.hms.model.Doctor;
import com.hms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDAO {

    @Autowired
    private Connection connection;


    // Insert new doctor
    public void insertDoctor(Doctor doctor) throws SQLException {
        String query = "INSERT INTO doctors(first_name, last_name, specialty, phone_number, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, doctor.getFirstName());
        ps.setString(2, doctor.getLastName());
        ps.setString(3, doctor.getSpecialty());
        ps.setString(4, doctor.getPhoneNumber());
        ps.setString(5, doctor.getEmail());
        ps.executeUpdate();
    }

    // Retrieve all doctors
    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Doctor d = new Doctor();
            d.setId(rs.getInt("id"));
            d.setFirstName(rs.getString("first_name"));
            d.setLastName(rs.getString("last_name"));
            d.setSpecialty(rs.getString("specialty"));
            d.setPhoneNumber(rs.getString("phone_number"));
            d.setEmail(rs.getString("email"));
            d.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            doctors.add(d);
        }
        return doctors;
    }

    // List doctors with number of patients
    public List<String> getDoctorsPatientCount() throws SQLException {
        List<String> result = new ArrayList<>();
        String query = """
            SELECT d.first_name, d.last_name, COUNT(DISTINCT a.patient_id) AS patient_count
            FROM doctors d
            LEFT JOIN appointments a ON d.id = a.doctor_id
            GROUP BY d.id
        """;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            result.add(rs.getString("first_name") + " " + rs.getString("last_name") +
                    " - Patients: " + rs.getInt("patient_count"));
        }
        return result;
    }

    // Doctors who have more than 5 patients
    public List<Doctor> getDoctorsWithMoreThanFivePatients() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        String query = """
            SELECT d.*
            FROM doctors d
            JOIN appointments a ON d.id = a.doctor_id
            GROUP BY d.id
            HAVING COUNT(DISTINCT a.patient_id) > 5
        """;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Doctor d = new Doctor();
            d.setId(rs.getInt("id"));
            d.setFirstName(rs.getString("first_name"));
            d.setLastName(rs.getString("last_name"));
            d.setSpecialty(rs.getString("specialty"));
            d.setPhoneNumber(rs.getString("phone_number"));
            d.setEmail(rs.getString("email"));
            d.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            doctors.add(d);
        }
        return doctors;
    }
}