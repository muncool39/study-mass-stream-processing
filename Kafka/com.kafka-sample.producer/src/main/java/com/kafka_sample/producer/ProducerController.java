package com.kafka_sample.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    /**
     * 카프카에 발송
     * @param topic     토픽: 메시지 저장 장소
     * @param key       키: 메시지를 토픽의 특정 파티션에 할당
     * @param message   메시지
     */
    @GetMapping("/send")
    public String sendMessage(
            @RequestParam("topic") String topic,
            @RequestParam("key") String key,
            @RequestParam("message") String message
    ) {
        producerService.sendMessage(topic, key, message);
        return "Message sent to Kafka topic";
    }
}