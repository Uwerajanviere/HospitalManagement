package com.hms.service;

import com.hms.dao.DoctorDAO;
import com.hms.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorDAO doctorDAO;

    public void addDoctor(Doctor doctor) throws SQLException {
        doctorDAO.insertDoctor(doctor);
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        return doctorDAO.getAllDoctors();
    }

    public List<String> getDoctorsPatientCount() throws SQLException {
        return doctorDAO.getDoctorsPatientCount();
    }

    public List<Doctor> getDoctorsWithMoreThanFivePatients() throws SQLException {
        return doctorDAO.getDoctorsWithMoreThanFivePatients();
    }
}