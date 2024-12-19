package com.meditrack.patient_record_service.controller;

import com.meditrack.patient_record_service.entity.Patient;
import com.meditrack.patient_record_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing patient records.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/patient-records")
public class PatientController {

    private final PatientService patientService;

    /**
     * Create a new patient record.
     *
     * @param patient the patient data.
     * @return ResponseEntity with the created Patient entity.
     */
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        log.info("Creating a new patient: {}", patient);
        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    /**
     * Update an existing patient record.
     *
     * @param id      the ID of the patient to update.
     * @param patient the updated patient data.
     * @return ResponseEntity with the updated Patient entity.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable String id, @RequestBody Patient patient) {
        log.info("Updating patient with ID: {}, Data: {}", id, patient);
        Patient updatedPatient = patientService.updatePatient(id, patient);
        return ResponseEntity.ok(updatedPatient);
    }

    /**
     * Retrieve a patient record by ID.
     *
     * @param id the ID of the patient.
     * @return ResponseEntity with the Patient entity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        log.info("Fetching patient with ID: {}", id);
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    /**
     * Retrieve all patient records.
     *
     * @return ResponseEntity with a list of all Patient entities.
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        log.info("Fetching all patients");
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    /**
     * Delete a patient record by ID.
     *
     * @param id the ID of the patient to delete.
     * @return ResponseEntity with no content on success.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        log.info("Deleting patient with ID: {}", id);
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
