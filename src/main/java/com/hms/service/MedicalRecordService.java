package com.hms.service;

import com.hms.dao.MedicalRecordDAO;
import com.hms.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordDAO medicalRecordDAO;

    public void addMedicalRecord(MedicalRecord record) throws SQLException {
        medicalRecordDAO.insertMedicalRecord(record);
    }

    public List<MedicalRecord> getRecordsByPatient(int patientId) throws SQLException {
        return medicalRecordDAO.getRecordsByPatient(patientId);
    }

    public List<Integer> getPatientsDiagnosedMultipleTimes() throws SQLException {
        return medicalRecordDAO.getPatientsDiagnosedMultipleTimes();
    }
}