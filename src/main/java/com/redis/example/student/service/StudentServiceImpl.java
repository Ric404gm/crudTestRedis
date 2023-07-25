package com.redis.example.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.example.student.persistence.Student;
import com.redis.example.student.persistence.StudentRepository;
import com.redis.example.student.persistence.Student.Gender;

@Service
public class StudentServiceImpl  implements StudentService {


    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    
    private StudentRepository repository;

    
    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }


    @Override
    public String delete(String id) {
        // TODO Auto-generated method stub
        this.repository.deleteById(id);
        return id ;
    }


    @Override
    public List<Student> listAll() {
        // TODO Auto-generated method stub
        List<Student> students = new  ArrayList<>();
           this.repository.findAll().forEach( students::add);
           return students;
    }


    @Override
    public Student newStudent(Student student) {
        return this.repository.save(student);
    }


    @Override
    public Student update(String id, Student student) {

        Optional<Student> studentResult = this.repository.findById(id);
        if(studentResult.isPresent()){
            
            Student studentConcrete = studentResult.get();  
            studentConcrete.setGender(student.getGender());
            studentConcrete.setGrade(student.getGrade());
            studentConcrete.setName("JulietsRepsosteria");
            
            return this.repository.save(studentConcrete);

        }else{
            return null;
        }

    }


    
    

}
