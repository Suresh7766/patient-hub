package com.patient.hub.patienthub.service;

import com.patient.hub.patienthub.dto.PatientDTO;
import com.patient.hub.patienthub.entity.Patient;
import com.patient.hub.patienthub.exception.NotFoundException;
import com.patient.hub.patienthub.repository.PatientRepository;
import com.patient.hub.patienthub.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePatient() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("John");
        patientDTO.setLastName("Doe");
        patientDTO.setAge(35);
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);

        Mockito.when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientDTO savedPatient = patientService.savePatient(patientDTO);

        assertEquals(patientDTO, savedPatient);
    }

    @Test
    public void testGetPatients() {
        List<Patient> patients = Arrays.asList(new Patient(), new Patient());
        Mockito.when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDTO> patientDTOs = patientService.getPatients();

        assertEquals(patients.size(), patientDTOs.size());
    }

    @Test
    public void testGetPatientById() {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setFirstName("Alice");
        patient.setLastName("Smith");

        Optional<Patient> optionalPatient = Optional.of(patient);
        Mockito.when(patientRepository.findById(1)).thenReturn(optionalPatient);

        PatientDTO patientDTO = patientService.getPatientById(1);

        assertEquals(patient.getId(), patientDTO.getId());
        assertEquals(patient.getFirstName(), patientDTO.getFirstName());
        assertEquals(patient.getLastName(), patientDTO.getLastName());
    }

    @Test
    public void testGetPatientByIdNotFound() {
        Mockito.when(patientRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            patientService.getPatientById(1);
        });

        assertEquals("Patient not found!", exception.getMessage());
    }

    @Test
    public void testUpdatePatient() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1);
        patientDTO.setFirstName("Bob");
        patientDTO.setLastName("Johnson");
        patientDTO.setAge(45);

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO, patient);

        Optional<Patient> optionalPatient = Optional.of(patient);
        Mockito.when(patientRepository.findById(1)).thenReturn(optionalPatient);
        Mockito.when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        assertDoesNotThrow(() -> {
            patientService.updatePatient(patientDTO);
        });
    }

    @Test
    public void testUpdatePatientNotFound() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(1);

        Mockito.when(patientRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            patientService.updatePatient(patientDTO);
        });

        assertEquals("Patient not found!", exception.getMessage());
    }
}
