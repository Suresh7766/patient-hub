package com.patient.hub.patienthub.service.impl;

import com.patient.hub.patienthub.dto.DoctorDTO;
import com.patient.hub.patienthub.entity.Doctor;
import com.patient.hub.patienthub.exception.NotFoundException;
import com.patient.hub.patienthub.repository.DoctorRepository;
import com.patient.hub.patienthub.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;


    @Override
    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();

        BeanUtils.copyProperties(doctorDTO, doctor, "id");

        BeanUtils.copyProperties(doctorRepository.save(doctor), doctorDTO);

        return doctorDTO;
    }


    @Override
    public List<DoctorDTO> getDoctors() {
        return doctorRepository.findAll().stream().map(doctor -> {
            DoctorDTO doctorDTO = new DoctorDTO();
            BeanUtils.copyProperties(doctor, doctorDTO);
            return doctorDTO;
        }).toList();
    }

    @Override
    public DoctorDTO getDoctorById(Integer id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor not found!"));
        DoctorDTO doctorDTO = new DoctorDTO();
        BeanUtils.copyProperties(doctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    public void updateDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.findById(doctorDTO.getId()).orElseThrow(() -> new NotFoundException("Patient not found!"));
        BeanUtils.copyProperties(doctorDTO, doctor);
        doctorRepository.save(doctor);
    }
}
