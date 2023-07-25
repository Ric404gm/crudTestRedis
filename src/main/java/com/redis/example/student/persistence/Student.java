package com.redis.example.student.persistence;

import org.springframework.data.redis.core.RedisHash;

import lombok.Data;


@Data
@RedisHash("Student")
public class Student {
    
    public enum Gender { 
        MALE, FEMALE
    }

    private String id;
    private String name;
    private Gender gender;
    private int grade;
    public Student(String id, String name, Gender gender, int grade) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.grade = grade;
    }


    
}
