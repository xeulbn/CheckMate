package org.example.checkmate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AIResponseDto {
    private int prediction;
    private double confidence;
}
