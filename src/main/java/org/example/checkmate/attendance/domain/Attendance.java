package org.example.checkmate.attendance.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.checkmate.lecture.domain.Lecture;
import org.example.checkmate.user.domain.User;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @ManyToOne
    private User student;

    @ManyToOne
    private Lecture lecture;
}
