package com.hospital.patientapi.service;

import com.hospital.patientapi.model.Patient;
import com.hospital.patientapi.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final PatientRepository patientRepository;

    public ValidationResponse validPatientToInsert(Patient patient) {
        if (patientRepository.existsById(patient.getId())) {
            return new ValidationResponse(false, "Patient with ID " + patient.getId() + " already exists!");
        }
        if (patient.getDiagnosis() == null) {
            return new ValidationResponse(false, "Patient diagnosis is required!");
        }
        if (patient.getFirstName() == null && patient.getLastName() == null) {
            return new ValidationResponse(false, "Patient name is required!");
        }

        return new ValidationResponse(true, null);
    }

    public ValidationResponse validPatientToUpdate(Patient patient, Long id) {
        if (patient.getId() != null && !patient.getId().equals(id)) {
            return new ValidationResponse(false, "Patient ID is READ-ONLY!");
        }
        if (patient.getId() == null)
            patient.setId(id);
        return new ValidationResponse(true, null);
    }
}
