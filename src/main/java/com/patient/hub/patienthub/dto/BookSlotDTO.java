package com.patient.hub.patienthub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookSlotDTO {

    @NotNull(message = "doctorId cannot be null or empty")
    private Integer doctorId;

    @NotNull(message = "patientId cannot be null or empty")
    private Integer patientId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "fromDateTime cannot be null or empty")
    @FutureOrPresent(message = "fromDateTime should be future or present date")
    private LocalDateTime fromDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "toDateTime cannot be null or empty")
    @FutureOrPresent(message = "toDateTime should be future or present date")
    private LocalDateTime toDateTime;
}
