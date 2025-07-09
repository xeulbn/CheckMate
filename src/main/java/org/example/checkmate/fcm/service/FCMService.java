package org.example.checkmate.fcm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FCMService {

    private final String firebaseApiUrl = "https://fcm.googleapis.com/fcm/send";
    private final String serverKey = "key=YOUR_SERVER_KEY"; // Firebase에서 발급받은 서버키

    public void sendWakeUpFCM(String deviceToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", serverKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> message = new HashMap<>();
        message.put("to", deviceToken);

        Map<String, Object> data = new HashMap<>();
        data.put("action", "wake_up"); // 앱에서 이 값을 보고 백그라운드에서 위치 인식

        message.put("data", data); // 알림이 아닌 data-only 메시지

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);
        restTemplate.postForEntity(firebaseApiUrl, request, String.class);
    }
}
