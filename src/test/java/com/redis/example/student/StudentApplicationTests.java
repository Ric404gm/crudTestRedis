package com.redis.example.student;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.redis.example.student.persistence.Student;
import com.redis.example.student.persistence.Student.Gender;
import com.redis.example.student.service.StudentService;

@SpringBootTest
class StudentApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentApplicationTests.class);

	@Autowired
	StudentService studentService;

	@Test
	@DisplayName(" * Test Crud Student * ")
	void  givenAnyStudent_Then_Update_Exists_And_Delete_WithVerification() 
	{


		//String result = studentService.newStudent();
		//assertThat(result.contains("Juliets"));
		UUID uuid = UUID.randomUUID();  
		Student student = new Student(uuid.toString(), " Julietsdd", Gender.MALE, 4);
		Student  result = studentService.newStudent( student );


		LOGGER.info(  " Result student : {}  " , result.toString());
		assertThat( result  ).isNotNull();
		assertThat( result.getId()  ).endsWith(uuid.toString());
		

		result.setId(null);
		result.setGrade(50);
		result.setName(" Juliets2");	
		result = studentService.update(uuid.toString(), result);
		assertThat( result.getId()  ).endsWith(uuid.toString());
		

		List<Student> resultStudens =   studentService.listAll();


		LOGGER.info(  " Result student as List : {}   : From service {}" ,Arrays.asList(result) , resultStudens );
		assertThat( resultStudens   ).contains(result);


		String uuilEliminado = studentService.delete( result.getId());

		resultStudens =   studentService.listAll();
		assertThat(resultStudens).as(" Not contais last deleted Item ").
		extracting( Student::getId).doesNotContain(uuilEliminado);










		
		
	}

}
