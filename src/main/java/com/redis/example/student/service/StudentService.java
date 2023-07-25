package com.redis.example.student.service;

import java.util.List;

import com.redis.example.student.persistence.Student;

public interface StudentService {
    
    Student newStudent(Student student );
    Student update(String   id,Student student );
    String delete(String   id);
    List <Student>  listAll();

}
