package org.example.checkmate.classschedule.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.checkmate.lecture.domain.Lecture;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name="classschedules")
@Getter@Setter
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;
    private LocalTime endTime;
    private String lectureRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lectures;
}
