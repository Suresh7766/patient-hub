package com.patient.hub.patienthub.controller;

import com.patient.hub.patienthub.dto.DoctorDTO;
import com.patient.hub.patienthub.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorDTO> saveDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.saveDoctor(doctorDTO));
    }

    @GetMapping("{id}")
    @Cacheable(value = "doctorCache", key = "#doctorId", condition="#doctorId!=null")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
        doctorService.updateDoctor(doctorDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Cacheable(value = "doctorCache", key = "#allDoctors", condition="#allDoctors!=null")
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

}
