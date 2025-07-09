package org.example.checkmate.lecture.service;

import lombok.RequiredArgsConstructor;
import org.example.checkmate.lecture.domain.Lecture;
import org.example.checkmate.lecture.dto.LectureRequestDto;
import org.example.checkmate.lecture.repository.LectureRepository;
import org.example.checkmate.user.domain.User;
import org.example.checkmate.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;

    public void createLecture(LectureRequestDto dto) {
        User professor = userRepository.findByUsername(dto.getProfessorUsername());

        Lecture lecture = new Lecture();
        lecture.setLectureName(dto.getLectureName());
        lecture.setLectureRoom(dto.getLectureRoom());
        lecture.setProfessor(professor);

        lectureRepository.save(lecture);
    }
}