package com.example.DoctorAppointmentAndMedicationManageSystem.controller;

import com.example.DoctorAppointmentAndMedicationManageSystem.entity.Patient;
import com.example.DoctorAppointmentAndMedicationManageSystem.repository.PatientRepository;
import com.example.DoctorAppointmentAndMedicationManageSystem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Patient patient=new Patient();
        model.addAttribute("patients",patient);
        return "registration";
    }

    @PostMapping("/saveRegistration")
    public String validateAndRegisterPatient(@Valid @RequestBody @ModelAttribute("patients") Patient patient) {
        // Logic to save the patient
        if (patient.getRole() == null || patient.getRole().isEmpty()) {
            patient.setRole("patient");
        }
        patientService.registerPatient(patient);
        System.out.println("Patient Registered: " + patient);
        return "redirect:/api/login";

    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @Value("${doctor.email}")
    private String doctorEmail;

    @Value("${doctor.password}")
    private String doctorPassword;

    @Value("${doctor.role}")
    private String doctorRole;

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("role") String role, Model model) {



        if (email.equals(doctorEmail) && password.equals(doctorPassword) && role.equals(doctorRole)) {
            return "doctor_portal";  // Redirect to doctor portal
        }

        Patient patient = patientRepository.findByEmailAndPasswordAndRole(email, password, role);
        if (patient != null) {
            return "patient_portal";  // Redirect to patient portal
        }
            return "redirect:/login?error";
        }
}

