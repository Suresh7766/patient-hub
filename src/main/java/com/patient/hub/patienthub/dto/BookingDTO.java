package com.patient.hub.patienthub.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingDTO {

    private Integer id;

    private DoctorDTO doctorDTO;

    private PatientDTO patientDTO;

    private LocalDateTime fromTime;

    private LocalDateTime toTime;

    private LocalDateTime bookedTime;
}
