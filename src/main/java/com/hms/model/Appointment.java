package com.hms.model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private int doctorId;
    private int patientId;
    private LocalDateTime appointmentDate;
    private String status;

    public Appointment(int i, int i1, LocalDateTime of, String scheduled) {
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}