package org.example.checkmate.classschedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private Long lectureId;
    private String dayOfWeek;      // ex) "MONDAY"
    private String startTime;      // ex) "10:00"
    private String endTime;        // ex) "11:30"
    private String lectureRoom;
}