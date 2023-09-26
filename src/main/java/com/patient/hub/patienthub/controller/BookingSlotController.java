package com.patient.hub.patienthub.controller;

import com.patient.hub.patienthub.dto.BookSlotDTO;
import com.patient.hub.patienthub.dto.BookingDTO;
import com.patient.hub.patienthub.service.BookingSlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
@RequiredArgsConstructor
public class BookingSlotController {

    private final BookingSlotService bookingSlotService;

    @PostMapping
    public ResponseEntity<BookingDTO> saveBooking(@RequestBody @Valid BookSlotDTO bookSlotDTO) {
       return ResponseEntity.status(HttpStatus.CREATED).body(bookingSlotService.saveBooking(bookSlotDTO));
    }

    @GetMapping("patient/{patientId}")
    public ResponseEntity<List<BookingDTO>> getPatientBookingById(@PathVariable Integer patientId) {
        return ResponseEntity.ok(bookingSlotService.getPatientBookingById(patientId));
    }

    @GetMapping("doctor/{doctorId}")
    public ResponseEntity<List<BookingDTO>> getDoctorBookingById(@PathVariable Integer doctorId) {
        return ResponseEntity.ok(bookingSlotService.getDoctorBookingById(doctorId));
    }
}
