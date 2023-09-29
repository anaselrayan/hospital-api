package com.hospital.patientapi.service;

import com.hospital.patientapi.model.Patient;
import com.hospital.patientapi.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> filterPatients(Map<String, String[]> params) {
        if (params.get("first_name") != null) {
            return findByFirstname(params.get("first_name")[0]);
        }
        if (params.get("last_name") != null) {
            return findByLastName(params.get("last_name")[0]);
        }
        if (params.get("gender") != null) {
            return findByGender(params.get("gender")[0]);
        }
        if (params.get("phone_number") != null) {
            return findByPhoneNumber(params.get("phone_number")[0]);
        }
        if (params.get("email") != null) {
            return findByEmail(params.get("email")[0]);
        }
        if (params.get("address") != null) {
            return findByAddress(params.get("address")[0]);
        }
        if (params.get("visit_data") != null) {
            return findByVisitDate(LocalDate.parse(params.get("first_name")[0]));
        }
        if (params.get("diagnosis") != null) {
            return findByDiagnosis(params.get("diagnosis")[0]);
        }
        if (params.get("drug_code") != null) {
            return findByDrugCode(params.get("drug_code")[0]);
        }
        if (params.get("new_patient") != null) {
            return findByNewPatient(Boolean.parseBoolean(params.get("address")[0]));
        }
        if (params.get("race") != null) {
            return findByRace(params.get("race")[0]);
        }
        if (params.get("ssn") != null) {
            return findBySsn(params.get("ssn")[0]);
        }
        return null;
    }
    
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }
    
    public List<Patient> findByFirstname(String firstname) {
        return patientRepository.findAllByFirstName(firstname);
    }

    public List<Patient> findByLastName(String lastname) {
        return patientRepository.findAllByLastName(lastname);
    }

    public List<Patient> findByGender(String gender) {
        return patientRepository.findAllByGender(gender);
    }

    public List<Patient> findByPhoneNumber(String phone) {
        return patientRepository.findAllByPhoneNumber(phone);
    }

    public List<Patient> findByEmail(String email) {
        return patientRepository.findAllByEmail(email);
    }

    public List<Patient> findByAddress(String address) {
        return patientRepository.findAllByAddress(address);
    }

    public List<Patient> findByVisitDate(LocalDate visitDate) {
        return patientRepository.findAllByVisitDate(visitDate);
    }

    public List<Patient> findByDiagnosis(String diagnosis) {
        return patientRepository.findAllByDiagnosis(diagnosis);
    }

    public List<Patient> findByDrugCode(String drugCode) {
        return patientRepository.findAllByDrugCode(drugCode);
    }

    public List<Patient> findBySsn(String ssn) {
        return patientRepository.findAllBySsn(ssn);
    }

    public List<Patient> findByRace(String race) {
        return patientRepository.findAllByRace(race);
    }

    public List<Patient> findByNewPatient(boolean newPatient) {
        return patientRepository.findAllByNewPatient(newPatient);
    }
    
    public Patient insertPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
