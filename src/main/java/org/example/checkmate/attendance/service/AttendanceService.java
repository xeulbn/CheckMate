package org.example.checkmate.attendance.service;

import lombok.AllArgsConstructor;
import org.example.checkmate.attendance.domain.Attendance;
import org.example.checkmate.attendance.domain.AttendanceStatus;
import org.example.checkmate.attendance.repository.AttendanceRepository;
import org.example.checkmate.classschedule.domain.ClassSchedule;
import org.example.checkmate.classschedule.repository.ClassScheduleRepository;
import org.example.checkmate.lecture.domain.Lecture;
import org.example.checkmate.lecture.repository.LectureRepository;
import org.example.checkmate.user.domain.User;
import org.example.checkmate.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final LectureRepository lectureRepository;
    private final ClassScheduleRepository classScheduleRepository;
    private final UserRepository userRepository;

    public void checkAttendance(String username, String aiDetectedRoom){
        User student = userRepository.findByUsername(username);

        LocalDateTime now = LocalDateTime.now();
        DayOfWeek today = now.getDayOfWeek();
        LocalTime currentTime = LocalTime.now();

        for(Lecture lecture : student.getLectures()){
            for(ClassSchedule schedule : lecture.getSchedules()){
                if(schedule.getDayOfWeek()==today && isWithin(schedule.getStartTime(),currentTime)){
                    if(schedule.getLectureRoom().equals(aiDetectedRoom)){
                        Attendance attendance = new Attendance();
                        attendance.setStudent(student);
                        attendance.setLecture(lecture);
                        attendance.setDate(now.toLocalDate());
                        attendance.setStatus(AttendanceStatus.PRESENT);
                        attendanceRepository.save(attendance);
                        return;
                    }
                }else if (schedule.getDayOfWeek()==today&&isLate(schedule.getStartTime(),currentTime)){
                    Attendance attendance = new Attendance();
                    attendance.setStudent(student);
                    attendance.setLecture(lecture);
                    attendance.setDate(now.toLocalDate());
                    attendance.setStatus(AttendanceStatus.LATE);
                    attendanceRepository.save(attendance);
                    return;
                }
            }
        }
    }

    private boolean isWithin(LocalTime classStart, LocalTime now){
        return now.isAfter(classStart.minusMinutes(10)) && now.isBefore(classStart.plusMinutes(5));
    }
    private boolean isLate(LocalTime classStart, LocalTime now){
        return now.isAfter(classStart.plusMinutes(5)) && now.isBefore(classStart.plusMinutes(15));
    }

    public List<Attendance> getAttendByUser(String username){
        return attendanceRepository.findByStudentUsername(username);
    }

    public List<Attendance> getAttendanceByLecture(Long lectureId){
        return attendanceRepository.findByLectureId(lectureId);
    }
}
