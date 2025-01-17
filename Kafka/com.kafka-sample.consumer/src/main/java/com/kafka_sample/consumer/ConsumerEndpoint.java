package com.kafka_sample.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerEndpoint {

    /*
     @KafkaListener: 해당 메서드를 Kafka 리스너로 설정하는 어노테이션
     */

    /**
     * Kafka 에서 메시지를 소비하는 리스너 메서드
     * Kafka 토픽 "topic1"에서 메시지를 수신하면 이 메서드가 호출
     * groupId: 컨슈머 그룹을 지정하여 동일한 그룹에 속한 다른 컨슈머와 메시지를 분배
     * @param message 수신 메시지
     */
    @KafkaListener(topics = "topic1", groupId = "group_a")
    public void consumeFromGroupA(String message) {
        log.info("Group A consumed message from topic1: {}", message);
    }

    /**
     * 동일한 토픽을 다른 그룹 ID로 소비하는 또 다른 리스너 메서드
     * @param message 수신 메시지
     */
    @KafkaListener(topics = "topic1", groupId = "group_b")
    public void consumeFromGroupB(String message) {
        log.info("Group B consumed message from topic1: {}", message);
    }

    /*
    다른 토픽, 같은 그룹 요청하는 경우
     */
    @KafkaListener(topics = "topic2", groupId = "group_c")
    public void consumeFromTopicC(String message) {
        log.info("Group C consumed message from topic2: {}", message);
    }

    @KafkaListener(topics = "topic3", groupId = "group_c")
    public void consumeFromTopicD(String message) {
        log.info("Group C consumed message from topic3: {}", message);
    }

    /**
     * 토픽과 그룹이 모두 다른 리스너 메서드
     * @param message 수신 메시지
     */
    @KafkaListener(topics = "topic4", groupId = "group_d")
    public void consumeFromPartition0(String message) {
        log.info("Group D consumed message from topic4: {}", message);
    }
}