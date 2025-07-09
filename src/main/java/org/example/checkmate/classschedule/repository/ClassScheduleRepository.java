package org.example.checkmate.classschedule.repository;

import org.example.checkmate.classschedule.domain.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Long> {

    List<ClassSchedule> findByLecturesId(Long lectureId);

}
