package org.example.checkmate.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class FrequencyRequestDto {
    private List<FrequencyDto> data;
}
