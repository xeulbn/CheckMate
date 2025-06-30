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
            // 실제 AI 서버 호출
            ResponseEntity<AIResponseDto> response = restTemplate.postForEntity(
                    aiServerUrl,
                    entity,
                    AIResponseDto.class
            );
            return response;
        } catch (Exception e) {
            log.error(" AI 서버 연결 실패. mock 응답을 반환합니다: {}", e.getMessage());

            //실패 시 mock 응답
            AIResponseDto mockResponse = new AIResponseDto();
            mockResponse.setPrediction(3);  // 임의의 강의실 번호
            mockResponse.setConfidence(0.0); // 자신도 없는 mock 응답

            return ResponseEntity.ok(mockResponse);
        }
    }
}