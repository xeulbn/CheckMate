package org.example.checkmate.aiconnect.aicontroller;

import lombok.RequiredArgsConstructor;
import org.example.checkmate.aiconnect.aiservice.AIService;
import org.example.checkmate.dto.FrequencyDto;
import org.example.checkmate.dto.FrequencyRequestDto;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("ai")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SendToAiController {

    private final AIService aiService;

    @PostMapping("/sendToAi")
    public ResponseEntity<?> sendToAi(@RequestBody FrequencyRequestDto requestDto) {
        return aiService.sendFrequencyToAI(requestDto);
    }
}
