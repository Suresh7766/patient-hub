package com.patient.hub.patienthub.controller;

import com.patient.hub.patienthub.dto.PatientDTO;
import com.patient.hub.patienthub.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
@RequestMapping("api/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientDTO> savePatient(@RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(patientDTO));
    }

    @GetMapping("{id}")
    @Cacheable(value = "patientCache", key = "#patientId", condition="#patientId!=null")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Integer id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping
    public void updatePatient(@RequestBody @Valid PatientDTO patientDTO) {
        patientService.updatePatient(patientDTO);
        ResponseEntity.noContent().build();
    }

    @GetMapping
    @Cacheable(value = "patientCache", key = "#allPatients", condition="#allPatients!=null")
    public ResponseEntity<List<PatientDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }
}
