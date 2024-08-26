package com.consumer.service;

import com.producer.avro.classes.Student;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StudentKafkaConsumer {

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(GenericRecord record) {
        // Convert GenericRecord to your Avro class if needed
        System.out.println("Received record: " + record);
        Student student = new Student();
        student.setId((Integer) record.get("id"));
        student.setStudentName((CharSequence) record.get("studentName"));
        student.setAge((Integer) record.get("age"));

        System.out.println("Student : --> " + student);
//        StudentModel studentModel = new StudentModel();
//        studentModel.setId((Integer) record.get("id"));
//        studentModel.setName((String) record.get("studentName"));
//        studentModel.setId((Integer) record.get("age"));
//        System.out.println("Student Module : --> " + studentModel);
    }
}
