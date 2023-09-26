package com.patient.hub.patienthub.service;

import com.patient.hub.patienthub.dto.BookSlotDTO;
import com.patient.hub.patienthub.dto.BookingDTO;

import java.util.List;

public interface BookingSlotService {
    BookingDTO saveBooking(BookSlotDTO bookSlotDTO);

    List<BookingDTO> getPatientBookingById(Integer patientId);

    List<BookingDTO> getDoctorBookingById(Integer doctorId);
}
