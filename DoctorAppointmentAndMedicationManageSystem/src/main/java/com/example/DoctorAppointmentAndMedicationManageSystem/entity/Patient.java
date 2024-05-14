package com.example.DoctorAppointmentAndMedicationManageSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Email
        @NotBlank(message = "Email cannot be blank")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;


        private String role; // DOCTOR or PATIENT


}
