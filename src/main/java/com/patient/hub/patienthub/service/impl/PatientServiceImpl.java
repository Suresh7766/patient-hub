package com.patient.hub.patienthub.service.impl;

import com.patient.hub.patienthub.dto.PatientDTO;
import com.patient.hub.patienthub.entity.Patient;
import com.patient.hub.patienthub.exception.NotFoundException;
import com.patient.hub.patienthub.repository.PatientRepository;
import com.patient.hub.patienthub.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;


    @Override
    public PatientDTO savePatient(PatientDTO patientDTO) {
        Patient patient = new Patient();

        BeanUtils.copyProperties(patientDTO, patient, "id");

        BeanUtils.copyProperties(patientRepository.save(patient), patientDTO);

        return patientDTO;
    }

    @Override
    public List<PatientDTO> getPatients() {
        return patientRepository.findAll().stream().map(patient -> {
            PatientDTO patientDTO = new PatientDTO();
            BeanUtils.copyProperties(patient, patientDTO);
            return patientDTO;
        }).toList();
    }

    @Override
    public PatientDTO getPatientById(Integer id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient not found!"));
        PatientDTO patientDTO = new PatientDTO();
        BeanUtils.copyProperties(patient, patientDTO);
        return patientDTO;
    }

    @Override
    public void updatePatient(PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(patientDTO.getId()).orElseThrow(() -> new NotFoundException("Patient not found!"));
        BeanUtils.copyProperties(patientDTO, patient);
        patientRepository.save(patient);
    }
}
