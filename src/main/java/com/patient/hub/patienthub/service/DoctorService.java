package com.patient.hub.patienthub.service;

import com.patient.hub.patienthub.dto.DoctorDTO;

import java.util.List;

public interface DoctorService {
    DoctorDTO saveDoctor(DoctorDTO doctorDTO);

    List<DoctorDTO> getDoctors();

    DoctorDTO getDoctorById(Integer id);

    void updateDoctor(DoctorDTO doctorDTO);
}
