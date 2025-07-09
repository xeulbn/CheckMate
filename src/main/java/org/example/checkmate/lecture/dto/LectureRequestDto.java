package org.example.checkmate.lecture.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureRequestDto {
    private String lectureName;
    private String lectureRoom;
    private String professorUsername;
}