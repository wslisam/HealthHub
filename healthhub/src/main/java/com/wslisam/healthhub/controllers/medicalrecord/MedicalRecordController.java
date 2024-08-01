package com.wslisam.healthhub.controllers.medicalrecord;

import com.wslisam.healthhub.models.medicalrecord.MedicalRecord;
import com.wslisam.healthhub.models.user.User;
import com.wslisam.healthhub.services.medicalrecord.MedicalRecordService;
import com.wslisam.healthhub.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;
    private final UserService userService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService, UserService userService) {
        this.medicalRecordService = medicalRecordService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords() {
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(medicalRecords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordService.getMedicalRecordById(id);
        return medicalRecord.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecordsByUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecordsByUser(user.get());
            return ResponseEntity.ok(medicalRecords);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicalRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable Long id, @RequestBody MedicalRecord medicalRecord) {
        Optional<MedicalRecord> existingMedicalRecord = medicalRecordService.getMedicalRecordById(id);
        if (existingMedicalRecord.isPresent()) {
            medicalRecord.setId(id);
            MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecord(medicalRecord);
            return ResponseEntity.ok(updatedMedicalRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        Optional<MedicalRecord> existingMedicalRecord = medicalRecordService.getMedicalRecordById(id);
        if (existingMedicalRecord.isPresent()) {
            medicalRecordService.deleteMedicalRecord(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}