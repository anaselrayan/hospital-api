package com.hospital.patientapi.resource;

import com.hospital.patientapi.model.Patient;
import com.hospital.patientapi.service.PatientService;
import com.hospital.patientapi.service.ValidationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientResource {
    private final PatientService patientService;
    private final ValidationService validationService;

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long id) {
        var res =  patientService.findById(id);
        if (res.isPresent())
            return new ResponseEntity<>(res.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>("Error: Patient doesn't exist.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchPatients(HttpServletRequest request) {
        var res = patientService.filterPatients(request.getParameterMap());
        if (res != null)
            return ResponseEntity.ok(res);
        return ResponseEntity.badRequest().body("Please Enter a valid parameters (e.g: last_name=John)");
    }

    @PostMapping
    public ResponseEntity<Object> addPatient(@RequestBody Patient patient) {
        var validationRes = validationService.validPatientToInsert(patient);
        if (validationRes.isValid()) {
            var res = patientService.insertPatient(patient);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body(validationRes.message());
    }

    @PutMapping({"{id}"})
    public ResponseEntity<Object> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        var validationRes = validationService.validPatientToUpdate(patient, id);
        if (validationRes.isValid()) {
            var res = patientService.updatePatient(patient);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.badRequest().body(validationRes.message());
    }

    @DeleteMapping("{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}