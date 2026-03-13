package com.hms.model;

import java.time.LocalDateTime;

public class MedicalRecord {
    private int id;
    private int patientId;
    private String diagnosis;
    private String treatment;
    private LocalDateTime recordDate;

    public MedicalRecord(int i, String flu, String restAndHydration, LocalDateTime now) {
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public LocalDateTime getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDateTime recordDate) { this.recordDate = recordDate; }
}