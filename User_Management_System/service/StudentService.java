package com.risrch.sms.service;

import java.util.List;

import com.risrch.sms.dto.StudentDto;


public interface StudentService {

	List<StudentDto> getAllStudents();

	void createStudent(StudentDto student);
	
	StudentDto getStudentById(Long studentId);
	
	void updateStudent(StudentDto stustudentDto);
	
	void deleteStudent(long studentId);
}
