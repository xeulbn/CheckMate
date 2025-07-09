package org.example.checkmate.classschedule.controller;


import lombok.RequiredArgsConstructor;
import org.example.checkmate.classschedule.dto.ScheduleRequestDto;
import org.example.checkmate.classschedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/create")
    public ResponseEntity<String> createSchedule(@RequestBody ScheduleRequestDto dto) {
        scheduleService.createSchedule(dto);
        return ResponseEntity.ok("시간표 등록 완료");
    }
}
