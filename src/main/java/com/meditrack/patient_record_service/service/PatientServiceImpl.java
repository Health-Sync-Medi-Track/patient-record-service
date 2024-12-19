package com.meditrack.patient_record_service.service;

import com.meditrack.patient_record_service.entity.Patient;
import com.meditrack.patient_record_service.exceprion.PatientNotFoundException;
import com.meditrack.patient_record_service.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing patient records.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    /**
     * Creates a new patient record.
     *
     * @param patient Patient data to be stored.
     * @return The created Patient entity.
     */
    @Override
    public Patient createPatient(Patient patient) {
        log.info("Creating new patient record: {}", patient.getFirstname());
        return this.patientRepository.save(patient);
    }

    /**
     * Updates an existing patient record by ID.
     *
     * @param id      ID of the patient to update.
     * @param patient Updated patient data.
     * @return The updated Patient entity.
     */
    @Override
    public Patient updatePatient(String id, Patient patient) {
        log.info("Updating patient record with ID: {}", id);
        return this.patientRepository.findById(id)
                .map(existingPatient -> {
                    patient.setId(id);
                    log.info("Existing patient found: {}. Updating record.", existingPatient.getFirstname());
                    return this.patientRepository.save(patient);
                })
                .orElseThrow(() -> {
                    log.warn("Patient record not found with ID: {}", id);
                    return new PatientNotFoundException("Patient not found with ID: " + id);
                });
    }

    /**
     * Retrieves a patient record by ID.
     *
     * @param id ID of the patient.
     * @return The Patient entity if found.
     */
    @Override
    public Patient getPatientById(String id) {
        log.info("Fetching patient record with ID: {}", id);
        return this.patientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID: {}", id);
                    return new PatientNotFoundException("Patient not found with ID: " + id);
                });
    }

    /**
     * Retrieves all patient records.
     *
     * @return List of all Patient entities.
     */
    @Override
    public List<Patient> getAllPatients() {
        log.info("Fetching all patient records");
        return this.patientRepository.findAll();
    }

    /**
     * Deletes a patient record by ID.
     *
     * @param id ID of the patient to delete.
     */
    @Override
    public void deletePatient(String id) {
        log.info("Deleting patient record with ID: {}", id);
        this.patientRepository.findById(id).ifPresentOrElse(
                patient -> {
                    this.patientRepository.deleteById(id);
                    log.info("Patient with ID: {} deleted successfully", id);
                },
                () -> {
                    log.warn("Patient with ID: {} not found for deletion", id);
                    throw new PatientNotFoundException("Patient not found with ID: " + id);
                });
    }
}
