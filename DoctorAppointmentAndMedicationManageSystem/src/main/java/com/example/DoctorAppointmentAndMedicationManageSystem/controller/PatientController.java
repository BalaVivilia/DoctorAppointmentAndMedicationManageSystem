package com.example.DoctorAppointmentAndMedicationManageSystem.controller;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Appointment;
import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Patient;
import com.example.DoctorAppointmentAndMedicationManageSystem.repository.PatientRepository;
import com.example.DoctorAppointmentAndMedicationManageSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class PatientController {

        @Autowired
        AppointmentService appointmentService;

        @Autowired
        PatientRepository patientRepository;

        @GetMapping("/patient/availableSlots")
        public String viewAvailableSlots(Model model) {
            List<Appointment> availableSlots = appointmentService.getAvailableSlots();  // Get all available slots
            model.addAttribute("availableSlots", availableSlots);

            return "book_available_slots";  // Return the available slots view for patients
        }

    @PostMapping("/patient/bookSlot")
    public String bookSlot(
            @RequestParam("slotId") Long slotId,
            @RequestParam("patientId") Long patientId
    ) {
        appointmentService.bookSlot(slotId, patientId);  // Book the slot
        return "redirect:/patient/availableSlots";  // Redirect to available slots (without the booked slot)
    }
}
