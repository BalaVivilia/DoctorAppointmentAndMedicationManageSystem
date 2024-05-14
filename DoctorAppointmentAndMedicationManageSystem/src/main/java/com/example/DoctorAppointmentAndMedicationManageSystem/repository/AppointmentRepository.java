package com.example.DoctorAppointmentAndMedicationManageSystem.repository;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Appointment;
import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    //List<Appointment> findByDoctorAndDateTime(Doctor doctor,LocalDateTime startTime,LocalDateTime endTime);

    // List<Appointment> findByDoctorAndPatientIsNull(Doctor doctor);  // Available slots
    //List<Appointment> findByDoctorAndPatientIsNotNull(Doctor doctor);  // Booked slots


    List<Appointment> findByBookedFalse();  // Get available slots (not booked)
    List<Appointment> findByBookedTrue();  // Get booked slots

}
