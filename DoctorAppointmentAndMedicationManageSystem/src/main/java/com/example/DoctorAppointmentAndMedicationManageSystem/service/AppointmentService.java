package com.example.DoctorAppointmentAndMedicationManageSystem.service;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Appointment;
import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Doctor;
import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Patient;
import com.example.DoctorAppointmentAndMedicationManageSystem.repository.AppointmentRepository;
import com.example.DoctorAppointmentAndMedicationManageSystem.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    PatientRepository patientRepo;

    public void addSlot(LocalDateTime startTime, LocalDateTime endTime) {
        Appointment slot = new Appointment();
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        appointmentRepo.save(slot);  // Save new slot
    }

    public List<Appointment> getAvailableSlots() {
        return appointmentRepo.findByBookedFalse();  // Get all available slots
    }

    public List<Appointment> getBookedSlots() {
        return appointmentRepo.findByBookedTrue();  // Get all booked slots
    }

    public void bookSlot(Long slotId, Long patientId) {
        Appointment slot = appointmentRepo.findById(slotId).orElseThrow(() -> new RuntimeException("Slot not found"));
        Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));

        slot.setPatient(patient);  // Assign the patient to the slot
        slot.setBooked(true);      // Mark the slot as booked

        appointmentRepo.save(slot);  // Save the changes
    }
}

