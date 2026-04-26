package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DemoController {

    private final MessageRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/hello")
    public String hello() {
        log.info("Received request on /api/hello");
        return "Hello from Spring Boot in Kubernetes!";
    }

    @PostMapping("/postgres")
    public MessageEntity saveToPostgres(@RequestBody String content) {
        MessageEntity saved = repository.save(new MessageEntity(content));
        log.info("Successfully saved entity with ID: {}", saved.getId());
        return saved;
    }

    @GetMapping("/postgres")
    public List<MessageEntity> getAllFromPostgres() {
        List<MessageEntity> messages = repository.findAll();
        log.info("Retrieved {} messages from database", messages.size());
        return messages;
    }

    @PostMapping("/kafka")
    public String sendToKafka(@RequestBody String message) {
        log.info("Received request to send message to Kafka: {}", message);
        kafkaTemplate.send("test-topic", message);
        log.info("Message sent successfully to Kafka topic 'test-topic'");
        return "Message sent to Kafka: " + message;
    }
}
