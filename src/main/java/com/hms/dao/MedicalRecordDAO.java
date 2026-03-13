package com.hms.dao;

import com.hms.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordDAO {

    @Autowired
    private Connection connection;

    // Insert medical record
    public void insertMedicalRecord(MedicalRecord record) throws SQLException {
        String query = "INSERT INTO medical_records(patient_id, diagnosis, treatment, record_date) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, record.getPatientId());
        ps.setString(2, record.getDiagnosis());
        ps.setString(3, record.getTreatment());
        ps.setTimestamp(4, Timestamp.valueOf(record.getRecordDate()));
        ps.executeUpdate();
    }

    // List all medical records for a patient
    public List<MedicalRecord> getRecordsByPatient(int patientId) throws SQLException {
        List<MedicalRecord> records = new ArrayList<>();
        String query = "SELECT * FROM medical_records WHERE patient_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, patientId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            MedicalRecord m = new MedicalRecord();
            m.setId(rs.getInt("id"));
            m.setPatientId(rs.getInt("patient_id"));
            m.setDiagnosis(rs.getString("diagnosis"));
            m.setTreatment(rs.getString("treatment"));
            m.setRecordDate(rs.getTimestamp("record_date").toLocalDateTime());
            records.add(m);
        }
        return records;
    }

    // Patients diagnosed more than once
    public List<Integer> getPatientsDiagnosedMultipleTimes() throws SQLException {
        List<Integer> patientIds = new ArrayList<>();
        String query = """
            SELECT patient_id
            FROM medical_records
            GROUP BY patient_id
            HAVING COUNT(*) > 1
        """;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            patientIds.add(rs.getInt("patient_id"));
        }
        return patientIds;
    }
}