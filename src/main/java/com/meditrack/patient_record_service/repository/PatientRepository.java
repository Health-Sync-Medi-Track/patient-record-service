package com.meditrack.patient_record_service.repository;

import com.meditrack.patient_record_service.entity.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Patient entities in MongoDB.
 */
@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
}
