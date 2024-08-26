package com.producer.avro.controller;

import com.producer.avro.classes.Student;
import com.producer.avro.producer.StudentKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/avro")
public class StudentController {

    @Autowired
    private StudentKafkaProducer service;

    @PostMapping(value = "/student")
//    public ResponseEntity<StudentModel> createStudentMethod(@RequestBody StudentModel studentModel){
    public ResponseEntity<Student> createStudentMethod(@RequestBody StudentModel studentModel){
        System.out.println("Sent student Model : " + studentModel);
        Student student = Student.newBuilder()
                .setId(studentModel.getId())
                .setStudentName(studentModel.getName()) // for default schema version
                .setAge(studentModel.getAge())
                .build();

        System.out.println("Sent student : " + student);
            service.sendStudent(student);
//            return new ResponseEntity<>(studentModel, HttpStatusCode.valueOf(201));
            return new ResponseEntity<>(student, HttpStatusCode.valueOf(201));
    }
}
