package com.example.DoctorAppointmentAndMedicationManageSystem.repository;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}
