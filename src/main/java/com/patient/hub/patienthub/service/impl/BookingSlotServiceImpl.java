package com.patient.hub.patienthub.service.impl;

import com.patient.hub.patienthub.dto.BookSlotDTO;
import com.patient.hub.patienthub.dto.BookingDTO;
import com.patient.hub.patienthub.dto.DoctorDTO;
import com.patient.hub.patienthub.dto.PatientDTO;
import com.patient.hub.patienthub.entity.BookingSlot;
import com.patient.hub.patienthub.entity.Doctor;
import com.patient.hub.patienthub.entity.Patient;
import com.patient.hub.patienthub.exception.NotFoundException;
import com.patient.hub.patienthub.repository.BookingSlotRepository;
import com.patient.hub.patienthub.repository.DoctorRepository;
import com.patient.hub.patienthub.repository.PatientRepository;
import com.patient.hub.patienthub.service.BookingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingSlotServiceImpl implements BookingSlotService {

    private final BookingSlotRepository bookingSlotRepository;

    private final PatientRepository patientRepository;

    private final DoctorRepository doctorRepository;

    @Override
    public BookingDTO saveBooking(BookSlotDTO bookSlotDTO) {
        Patient patient = patientRepository.findById(bookSlotDTO.getPatientId()).orElseThrow(() -> new NotFoundException("Patient not found!."));
        Doctor doctor = doctorRepository.findById(bookSlotDTO.getDoctorId()).orElseThrow(() -> new NotFoundException("Doctor not found!."));
        BookingSlot bookingSlot = new BookingSlot();
        BeanUtils.copyProperties(bookSlotDTO, bookingSlot);
        bookingSlot.setDoctor(doctor);
        bookingSlot.setPatient(patient);
        BookingSlot slot = bookingSlotRepository.save(bookingSlot);
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(slot.getDoctor(), doctorDTO);
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(slot.getPatient(), patientDTO);
        return BookingDTO.builder().id(slot.getId()).bookedTime(slot.getCreatedTimeStamp())
                .doctorDTO(doctorDTO).patientDTO(patientDTO).fromTime(slot.getFromDateTime())
                .toTime(slot.getToDateTime()).build();
    }

    @Override
    public List<BookingDTO> getPatientBookingById(Integer patientId) {

        return convertBookingSlotToBookingDTO(bookingSlotRepository.findByPatientId(patientId));
    }

    @Override
    public List<BookingDTO> getDoctorBookingById(Integer doctorId) {

        return convertBookingSlotToBookingDTO(bookingSlotRepository.findByDoctorId(doctorId));
    }

    static List<BookingDTO> convertBookingSlotToBookingDTO(List<BookingSlot> bookingSlots) {
        return bookingSlots.stream().map(bookingSlot -> {
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(bookingSlot.getDoctor(), doctorDTO);
            PatientDTO patientDTO = new PatientDTO();
            BeanUtils.copyProperties(bookingSlot.getPatient(), patientDTO);
            return BookingDTO.builder().id(bookingSlot.getId()).bookedTime(bookingSlot.getCreatedTimeStamp())
                    .doctorDTO(doctorDTO).patientDTO(patientDTO).fromTime(bookingSlot.getFromDateTime())
                    .toTime(bookingSlot.getToDateTime()).build();
        }).toList();
    }

}
