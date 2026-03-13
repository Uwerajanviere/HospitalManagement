package com.hms.dao;

import com.hms.model.*;
import com.hms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class MainApp {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }

    // This runs AFTER Spring Boot is fully started
    @EventListener(ApplicationReadyEvent.class)
    public void runApp() {
        try {
            System.out.println("=== Hospital Management System Demo ===");

            // Sample doctors
            Doctor d1 = new Doctor("John", "Doe", "Cardiology", "0781234567", "john@hospital.com");
            Doctor d2 = new Doctor("Mary", "Smith", "Pediatrics", "0789876543", "mary@hospital.com");
            doctorService.addDoctor(d1);
            doctorService.addDoctor(d2);

            // Sample patients
            Patient p1 = new Patient("Alice", "Johnson", LocalDate.of(1990,5,12), "Female", "0781112222", "alice@gmail.com");
            Patient p2 = new Patient("Bob", "Brown", LocalDate.of(1985,8,23), "Male", "0783334444", "bob@gmail.com");
            patientService.addPatient(p1);
            patientService.addPatient(p2);

            // Sample appointments
            Appointment a1 = new Appointment(1,1, LocalDateTime.of(2026,3,15,10,0), "Scheduled");
            Appointment a2 = new Appointment(2,2, LocalDateTime.of(2026,3,16,14,30), "Scheduled");
            appointmentService.addAppointment(a1);
            appointmentService.addAppointment(a2);

            // Sample medical records
            MedicalRecord m1 = new MedicalRecord(1,"Flu","Rest and hydration", LocalDateTime.now());
            MedicalRecord m2 = new MedicalRecord(2,"Allergy","Antihistamines", LocalDateTime.now());
            medicalRecordService.addMedicalRecord(m1);
            medicalRecordService.addMedicalRecord(m2);

            // Sample query: Patients of doctor 1
            System.out.println("--- Patients of Doctor 1 ---");
            patientService.getPatientsByDoctor(1).forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}