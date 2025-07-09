package org.example.checkmate.lecture.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.checkmate.classschedule.domain.ClassSchedule;
import org.example.checkmate.user.domain.User;

import java.util.List;

@Entity
@Table(name="lectures")
@Getter
@Setter
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lectureName;
    private String lectureRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    private User professor;

    @OneToMany(mappedBy = "lectures", cascade = CascadeType.ALL)
    private List<ClassSchedule> schedules;

}
