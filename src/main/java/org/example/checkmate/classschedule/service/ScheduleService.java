package org.example.checkmate.classschedule.service;

import lombok.RequiredArgsConstructor;
import org.example.checkmate.classschedule.domain.ClassSchedule;
import org.example.checkmate.classschedule.dto.ScheduleRequestDto;
import org.example.checkmate.classschedule.repository.ClassScheduleRepository;
import org.example.checkmate.lecture.domain.Lecture;
import org.example.checkmate.lecture.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final LectureRepository lectureRepository;
    private final ClassScheduleRepository scheduleRepository;

    public void createSchedule(ScheduleRequestDto dto) {
        Lecture lecture = lectureRepository.findById(dto.getLectureId())
                .orElseThrow(() -> new RuntimeException("해당 강의 없음"));

        ClassSchedule schedule = new ClassSchedule();
        schedule.setLectureRoom(dto.getLectureRoom());
        schedule.setDayOfWeek(DayOfWeek.valueOf(dto.getDayOfWeek().toUpperCase()));
        schedule.setStartTime(LocalTime.parse(dto.getStartTime()));
        schedule.setEndTime(LocalTime.parse(dto.getEndTime()));
        schedule.setLectures(lecture);

        scheduleRepository.save(schedule);
    }
}