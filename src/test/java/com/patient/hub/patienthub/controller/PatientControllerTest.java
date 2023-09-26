package com.patient.hub.patienthub.controller;

import com.patient.hub.patienthub.dto.PatientDTO;
import com.patient.hub.patienthub.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientServiceImpl patientService;

    public static final int ID = 1;


    @Test
    void savePatient() {
        PatientDTO patientDTO = Mockito.mock(PatientDTO.class);
        Mockito.when(patientDTO.getId()).thenReturn(ID);
        Mockito.when(patientService.savePatient(patientDTO)).thenReturn(patientDTO);
        ResponseEntity<PatientDTO> responseEntity = patientController.savePatient(patientDTO);
        Assertions.assertEquals(ID, responseEntity.getBody().getId());
    }

    @Test
    void getPatientById() {
        PatientDTO patientDTO = Mockito.mock(PatientDTO.class);
        Mockito.when(patientDTO.getId()).thenReturn(ID);
        Mockito.when(patientService.getPatientById(ID)).thenReturn(patientDTO);
        ResponseEntity<PatientDTO> patient = patientController.getPatientById(ID);
        Assertions.assertEquals(ID, patient.getBody().getId());
    }

    @Test
    void updatePatient() {
        PatientDTO patientDTO = Mockito.mock(PatientDTO.class);
        Mockito.doNothing().when(patientService).updatePatient(patientDTO);
        patientController.updatePatient(patientDTO);
        Mockito.verify(patientService).updatePatient(patientDTO);
    }

    @Test
    void getPatients() {
        PatientDTO patientDTO = Mockito.mock(PatientDTO.class);
        Mockito.when(patientDTO.getId()).thenReturn(ID);
        Mockito.when(patientService.getPatients()).thenReturn(Arrays.asList(patientDTO));
        ResponseEntity<List<PatientDTO>> patients = patientController.getPatients();
        Assertions.assertEquals(ID, patients.getBody().get(0).getId());
    }
}