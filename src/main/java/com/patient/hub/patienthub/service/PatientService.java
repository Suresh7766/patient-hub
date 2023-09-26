package com.patient.hub.patienthub.service;

import com.patient.hub.patienthub.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    PatientDTO savePatient(PatientDTO patientDTO);

    PatientDTO getPatientById(Integer id);

    void updatePatient(PatientDTO patientDTO);

    List<PatientDTO> getPatients();
}
