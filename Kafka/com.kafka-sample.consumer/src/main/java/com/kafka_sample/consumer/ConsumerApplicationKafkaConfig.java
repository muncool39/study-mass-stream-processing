package com.kafka_sample.consumer;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/*
Kafka 컨슈머 설정을 위한 Spring 설정 클래스
@EnableKafka: Kafka 의 리스너를 활성화하는 어노테이션
 */
@EnableKafka
@Configuration
public class ConsumerApplicationKafkaConfig {


    /**
     * Kafka 컨슈머 팩토리를 생성하는 빈 정의
     * ConsumerFactory: Kafka 컨슈머 인스턴스를 생성하는 데 사용
     * 각 컨슈머는 해당 팩토리를 통해 생성된 설정을 기반으로 작동한다.
     * @return KafkaConsumerFactory 설정 반영된 팩토리
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        // 컨슈머 팩토리 설정을 위한 맵을 생성
        Map<String, Object> configProps = new HashMap<>();
        // Kafka 브로커의 주소를 설정
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 메시지 키의 디시리얼라이저 클래스를 설정
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 메시지 값의 디시리얼라이저 클래스를 설정
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 설정된 프로퍼티로 DefaultKafkaConsumerFactory 생성하여 반환
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    /**
     * Kafka 리스너 컨테이너 팩토리를 생성하는 빈을 정의
     * ConcurrentKafkaListenerContainerFactory:  Kafka 메시지를 비동기적으로 수신하는 리스너 컨테이너를 생성하는 데 사용
     * 해당 팩토리는 @KafkaListener 어노테이션이 붙은 메서드들을 실행할 컨테이너를 제공한다
     * @return ListenerContainerFactory 설정 반영된 리스너 컨테이너 팩토리
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        // ConcurrentKafkaListenerContainerFactory 를 생성
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // 컨슈머 팩토리를 리스너 컨테이너 팩토리에 설정
        factory.setConsumerFactory(consumerFactory());
        // 설정된 리스너 컨테이너 팩토리를 반환
        return factory;
    }
}
