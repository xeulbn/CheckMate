package org.example.checkmate.lecture.controller;

import lombok.RequiredArgsConstructor;
import org.example.checkmate.lecture.dto.LectureRequestDto;
import org.example.checkmate.lecture.service.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lectures")
public class LectureController {

    private final LectureService lectureService;

    @PostMapping("/create")
    public ResponseEntity<String> createLecture(@RequestBody LectureRequestDto dto) {
        lectureService.createLecture(dto);
        return ResponseEntity.ok("강의 개설 완료");
    }


}
