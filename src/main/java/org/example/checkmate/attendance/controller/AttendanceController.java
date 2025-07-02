package org.example.checkmate.attendance.controller;

import lombok.RequiredArgsConstructor;
import org.example.checkmate.attendance.dto.AttendanceRequestDto;
import org.example.checkmate.attendance.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/check")
    public ResponseEntity<String> checkAttendance(@RequestBody AttendanceRequestDto requestDto){
        attendanceService.checkAttendance(requestDto.getUsername(), requestDto.getDetectedRoom());
        return ResponseEntity.ok("출석 처리 완료");
    }
}
