package com.meditrack.patient_record_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity class representing the patient data stored in the database.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "patients")
public class Patient {
    @Id
    private String id;
    private String patientIdentifier;
    private String firstname;
    private String lastname;
    private int age;
    private int gender;
    private String contactNumber;
    private List<String> medicalHistory;
    private List<String> prescriptions;
    private List<String> labResults;

}
