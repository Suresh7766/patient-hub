package com.patient.hub.patienthub.repository;

import com.patient.hub.patienthub.entity.BookingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface BookingSlotRepository extends JpaRepository<BookingSlot, Integer> {
    
    List<BookingSlot> findByPatientId(Integer patientId);

    List<BookingSlot> findByDoctorId(Integer doctorId);
}
