package com.example.DoctorAppointmentAndMedicationManageSystem.controller;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Appointment;
import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Doctor;
import com.example.DoctorAppointmentAndMedicationManageSystem.repository.DoctorRepository;
import com.example.DoctorAppointmentAndMedicationManageSystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api")
public class DoctorController {

        @Autowired
        AppointmentService appointmentService;

        @Autowired
        private DoctorRepository doctorRepository;


        @GetMapping("/doctor/addSlotForm")
        public String addSlotForm(Model model){
            return "add_available_slot";

        }

        @PostMapping("/doctor/addSlot")
        public String addSlot(
                @RequestParam("startTime") LocalDateTime startTime,
                @RequestParam("endTime") LocalDateTime endTime
        ) {
            appointmentService.addSlot(startTime, endTime);  // Add new slot
            return "view_booked_slots";  // Redirect back to the portal after adding a slot
        }

        @GetMapping("/doctor/bookedSlots")
        public String showDoctorPortal(Model model) {
            List<Appointment> bookedSlots = appointmentService.getBookedSlots();  // Retrieve booked slots
            model.addAttribute("bookedSlots", bookedSlots);

            return "view_booked_slots";  // Return the portal page for doctors
        }
}
