package com.risrch.sms.mapper;

import com.risrch.sms.dto.StudentDto;
import com.risrch.sms.entity.Student;

public class StudentMapper {

	public static StudentDto mapToStudentDto(Student student)
	{
		StudentDto studentDto = new StudentDto(
				
								student.getId(),
								student.getFirstname(),
								student.getLastName(),
								student.getEmail()
				
				);
		
		return studentDto;
	}
	
	
	public static Student mapToStudent(StudentDto studentDto)
	{
		Student student = new Student(
					
					studentDto.getId(),
					studentDto.getFirstName(),
					studentDto.getLastName(),
					studentDto.getEmail()
				);
		
		return student;
	}
}
