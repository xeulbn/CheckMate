package org.example.checkmate.attendance.repository;

import org.example.checkmate.attendance.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentUsername(String username);
    List<Attendance> findByLectureId(Long lectureId);
}
