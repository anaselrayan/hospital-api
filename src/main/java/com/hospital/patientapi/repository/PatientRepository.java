package com.hospital.patientapi.repository;

import com.hospital.patientapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAllByFirstName(String firstname);

    List<Patient> findAllByLastName(String lastname);

    List<Patient> findAllByGender(String gender);

    List<Patient> findAllByPhoneNumber(String phone);

    List<Patient> findAllByEmail(String email);

    List<Patient> findAllByAddress(String address);

    List<Patient> findAllByVisitDate(LocalDate visitDate);

    List<Patient> findAllByDiagnosis(String diagnosis);

    List<Patient> findAllByDrugCode(String drugCode);

    List<Patient> findAllBySsn(String ssn);

    List<Patient> findAllByRace(String race);

    List<Patient> findAllByNewPatient(boolean newPatient);
}
