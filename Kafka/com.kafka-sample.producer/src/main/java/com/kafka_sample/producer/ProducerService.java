package com.kafka_sample.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    // 카프카 연결
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 메시지를 받으면 10개가 자동으로 카프카로 발송되도록 하는 메서드
     * @param topic     토픽
     * @param key       특정 파티션에 할당
     * @param message   보낼 메시지
     */
    public void sendMessage(String topic , String key, String message) {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send(topic, key, message + " " + i);
        }

    }
}