package com.wslisam.healthhub.repositories.medicalrecord;

import com.wslisam.healthhub.models.medicalrecord.MedicalRecord;
import com.wslisam.healthhub.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByUser(User user);
}