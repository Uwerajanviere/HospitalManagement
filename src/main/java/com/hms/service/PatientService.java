package com.hms.service;

import com.hms.dao.PatientDAO;
import com.hms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    public void addPatient(Patient patient) throws SQLException {
        patientDAO.insertPatient(patient);
    }

    public List<Patient> getAllPatients() throws SQLException {
        return patientDAO.getAllPatients();
    }

    public List<Patient> getPatientsByDoctor(int doctorId) throws SQLException {
        return patientDAO.getPatientsByDoctor(doctorId);
    }

    public void deletePatient(int patientId) throws SQLException {
        patientDAO.deletePatient(patientId);
    }
}