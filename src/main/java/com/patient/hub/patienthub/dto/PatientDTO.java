package com.patient.hub.patienthub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientDTO {

    private Integer id;

    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid Mobile Number")
    private String mobileNumber;

    @Email(message = "Invalid Email Format")
    private String email;

    @NotNull(message = "Patient Age is required")
    private Integer age;
}
