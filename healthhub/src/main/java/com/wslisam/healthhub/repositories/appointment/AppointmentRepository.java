package com.wslisam.healthhub.repositories.appointment;

import com.wslisam.healthhub.models.appointment.Appointment;
import com.wslisam.healthhub.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser(User user);
    List<Appointment> findByAppointmentTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Appointment> findByUserAndAppointmentTimeBetween(User user, LocalDateTime start, LocalDateTime end);
}