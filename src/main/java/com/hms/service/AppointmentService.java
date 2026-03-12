package com.hms.service;

import com.hms.dao.AppointmentDAO;
import com.hms.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    public void addAppointment(Appointment appointment) throws SQLException {
        appointmentDAO.insertAppointment(appointment);
    }

    public void updateAppointmentStatus(int appointmentId, String status) throws SQLException {
        appointmentDAO.updateStatus(appointmentId, status);
    }

    public List<String> getAppointmentsCountPerDoctor() throws SQLException {
        return appointmentDAO.getAppointmentsCountPerDoctor();
    }

    public List<String> getAppointmentsPerMonth() throws SQLException {
        return appointmentDAO.getAppointmentsPerMonth();
    }
}