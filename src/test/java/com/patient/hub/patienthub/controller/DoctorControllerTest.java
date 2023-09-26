package com.patient.hub.patienthub.controller;

import com.patient.hub.patienthub.dto.DoctorDTO;
import com.patient.hub.patienthub.service.impl.DoctorServiceImpl;
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

@SpringBootTest
public class DoctorControllerTest {

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorServiceImpl doctorService;

    public static final int ID = 1;

    @Test
    void saveDoctor() {
        DoctorDTO doctorDTO = Mockito.mock(DoctorDTO.class);
        Mockito.when(doctorDTO.getId()).thenReturn(ID);
        Mockito.when(doctorService.saveDoctor(doctorDTO)).thenReturn(doctorDTO);
        ResponseEntity<DoctorDTO> responseEntity = doctorController.saveDoctor(doctorDTO);
        Assertions.assertEquals(ID, responseEntity.getBody().getId());
    }

    @Test
    void getDoctorById() {
        DoctorDTO doctorDTO = Mockito.mock(DoctorDTO.class);
        Mockito.when(doctorDTO.getId()).thenReturn(ID);
        Mockito.when(doctorService.getDoctorById(ID)).thenReturn(doctorDTO);
        ResponseEntity<DoctorDTO> doctor = doctorController.getDoctorById(ID);
        Assertions.assertEquals(ID, doctor.getBody().getId());
    }

    @Test
    void updateDoctor() {
        DoctorDTO doctorDTO = Mockito.mock(DoctorDTO.class);
        Mockito.doNothing().when(doctorService).updateDoctor(doctorDTO);
        doctorController.updateDoctor(doctorDTO);
        Mockito.verify(doctorService).updateDoctor(doctorDTO);
    }

    @Test
    void getDoctors() {
        DoctorDTO doctorDTO = Mockito.mock(DoctorDTO.class);
        Mockito.when(doctorDTO.getId()).thenReturn(ID);
        Mockito.when(doctorService.getDoctors()).thenReturn(Arrays.asList(doctorDTO));
        ResponseEntity<List<DoctorDTO>> doctors = doctorController.getDoctors();
        Assertions.assertEquals(ID, doctors.getBody().get(0).getId());
    }
}