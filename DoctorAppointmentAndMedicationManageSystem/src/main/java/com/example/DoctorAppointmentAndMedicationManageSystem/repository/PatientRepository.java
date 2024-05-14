package com.example.DoctorAppointmentAndMedicationManageSystem.repository;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
        Patient findByEmailAndPasswordAndRole(String email, String password, String role);

}
