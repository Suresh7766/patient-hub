package com.patient.hub.patienthub.service;
import com.patient.hub.patienthub.dto.DoctorDTO;
import com.patient.hub.patienthub.entity.Doctor;
import com.patient.hub.patienthub.exception.NotFoundException;
import com.patient.hub.patienthub.exception.ResourceNotFoundException;
import com.patient.hub.patienthub.exception.ValidationException;
import com.patient.hub.patienthub.repository.DoctorRepository;
import com.patient.hub.patienthub.service.impl.DoctorServiceImpl;
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

public class DoctorServiceImplTest {

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @BeforeEach
    public void setUp() {
        // Initialize Mockito
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveDoctor() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setFullName("Dr. John");
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);

        Mockito.when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        DoctorDTO savedDoctor = doctorService.saveDoctor(doctorDTO);

        assertEquals(doctorDTO, savedDoctor);
    }

    @Test
    public void testSaveDoctorWithEmptyName() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setFullName("");

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            doctorService.saveDoctor(doctorDTO);
        });

        assertEquals("Name cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetDoctors() {
        List<Doctor> doctors = Arrays.asList(new Doctor(), new Doctor());
        Mockito.when(doctorRepository.findAll()).thenReturn(doctors);

        List<DoctorDTO> doctorDTOs = doctorService.getDoctors();

        assertEquals(doctors.size(), doctorDTOs.size());
    }

    @Test
    public void testGetDoctorById() {
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setFullName("Dr. Smith");

        Optional<Doctor> optionalDoctor = Optional.of(doctor);
        Mockito.when(doctorRepository.findById(1)).thenReturn(optionalDoctor);

        DoctorDTO doctorDTO = doctorService.getDoctorById(1);

        assertEquals(doctor.getId(), doctorDTO.getId());
        assertEquals(doctor.getFullName(), doctorDTO.getFullName());
    }

    @Test
    public void testGetDoctorByIdNotFound() {
        Mockito.when(doctorRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            doctorService.getDoctorById(1);
        });

        assertEquals("Doctor not found!", exception.getMessage());
    }

    @Test
    public void testUpdateDoctor() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(1);
        doctorDTO.setFullName("Dr. Watson");

        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO, doctor);

        Optional<Doctor> optionalDoctor = Optional.of(doctor);
        Mockito.when(doctorRepository.findById(1)).thenReturn(optionalDoctor);
        Mockito.when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        assertDoesNotThrow(() -> {
            doctorService.updateDoctor(doctorDTO);
        });
    }

    @Test
    public void testUpdateDoctorNotFound() {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(1);

        Mockito.when(doctorRepository.findById(1)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            doctorService.updateDoctor(doctorDTO);
        });

        assertEquals("Patient not found!", exception.getMessage());
    }
}
