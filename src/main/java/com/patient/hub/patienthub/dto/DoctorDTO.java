package com.patient.hub.patienthub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorDTO {

    private Integer id;

    @NotBlank(message = "Fullname cannot be blank")
    private String fullName;

    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid Mobile Number")
    private String mobileNumber;

    @NotBlank(message = "Specialist cannot be blank")
    private String specialist;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
