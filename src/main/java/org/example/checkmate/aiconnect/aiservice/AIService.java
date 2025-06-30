package org.example.checkmate.aiconnect.aiservice;

import lombok.extern.slf4j.Slf4j;
import org.example.checkmate.dto.AIResponseDto;
import org.example.checkmate.dto.FrequencyDto;
import org.example.checkmate.dto.FrequencyRequestDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AIService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String aiServerUrl = "https://48f5-223-195-38-62.ngrok-free.app/predict";

    public ResponseEntity<AIResponseDto> sendFrequencyToAI(FrequencyRequestDto frequencyRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<FrequencyRequestDto> entity = new HttpEntity<>(frequencyRequest, headers);

        try {
            ResponseEntity<AIResponseDto> response = restTemplate.postForEntity(
                    aiServerUrl,
                    entity,
                    AIResponseDto.class
            );
            return response;
        } catch (Exception e) {
            log.error("Error while communicating with AI server", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}