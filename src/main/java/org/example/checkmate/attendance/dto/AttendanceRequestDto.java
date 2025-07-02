package org.example.checkmate.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequestDto {
    private String username;
    private String detectedRoom;
}
