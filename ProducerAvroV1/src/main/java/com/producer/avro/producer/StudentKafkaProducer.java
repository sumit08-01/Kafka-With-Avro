package com.producer.avro.producer;

import com.producer.avro.classes.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentKafkaProducer {

    @Value("${topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;

    public void sendStudent(Student student) {
        String topicKey = UUID.randomUUID().toString();
        CompletableFuture<SendResult<String, Student>> sent = kafkaTemplate.send(topicName, topicKey, student);

        sent.whenComplete((result, ex) -> {
            if (ex == null) {
                // Message sent successfully
                System.out.println("Object of the topic: " + student);
                System.out.println("Sent to topic: " + result.getRecordMetadata().topic());
                System.out.println("Key is: " + result.getProducerRecord().key());
            } else {
                // Handle the exception
                System.out.println("Message not sent: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

}
