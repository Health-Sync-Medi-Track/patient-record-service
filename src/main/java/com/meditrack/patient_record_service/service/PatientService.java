package com.meditrack.patient_record_service.service;

import com.meditrack.patient_record_service.entity.Patient;

import java.util.List;

/**
 * Service interface defining methods for managing patient records.
 */
public interface PatientService {

    Patient createPatient(Patient patient);

    Patient updatePatient(String id, Patient patient);

    Patient getPatientById(String id);

    List<Patient> getAllPatients();

    void deletePatient(String id);
}
