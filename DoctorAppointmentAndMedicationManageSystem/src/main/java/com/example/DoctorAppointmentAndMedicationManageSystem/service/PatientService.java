package com.example.DoctorAppointmentAndMedicationManageSystem.service;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Patient;
import com.example.DoctorAppointmentAndMedicationManageSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepo;

    public void registerPatient(Patient patient) {
        patientRepo.save(patient);

    }
}
