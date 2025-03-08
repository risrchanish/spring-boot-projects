package com.risrch.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.risrch.sms.dto.StudentDto;
import com.risrch.sms.entity.Student;
import com.risrch.sms.mapper.StudentMapper;
import com.risrch.sms.repository.StudentRepository;
import com.risrch.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentRepository repository;
	
	
	public StudentServiceImpl(StudentRepository repository)
	{
		this.repository = repository;
	}
	
	@Override
	public List<StudentDto> getAllStudents() {
		
		List<Student> students = repository.findAll();
		
		
		
		/*
		 * traditional approach
		 * List<StudentDto> studentDto = new ArrayList<>();
		for(int i = 0; i < students.size(); i++)
		{
			studentDto.add(StudentMapper.mapToStudentDto(students.get(i)));
		}
		*/
		
		List<StudentDto> studentDto = students.stream()
				.map(student -> StudentMapper.mapToStudentDto(student))
				.collect(Collectors.toList());
		
		
		return studentDto;
	}

	@Override
	public void createStudent(StudentDto studentDto) {
		
		Student student = StudentMapper.mapToStudent(studentDto);
		repository.save(student);
		
		
	}

	@Override
	public StudentDto getStudentById(Long studentId) {
		
		Student student = repository.findById(studentId).get();
		StudentDto studentDto = StudentMapper.mapToStudentDto(student);
		return studentDto;
	}

	@Override
	public void updateStudent(StudentDto studentdto) {
		
		Student student = StudentMapper.mapToStudent(studentdto);
		repository.save(student);
	}

	@Override
	public void deleteStudent(long studentId) {
		
		repository.deleteById(studentId);
		
	}

	
}
