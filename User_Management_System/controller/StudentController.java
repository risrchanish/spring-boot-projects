package com.risrch.sms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.risrch.sms.dto.StudentDto;
import com.risrch.sms.service.StudentService;

import jakarta.validation.Valid;

@Controller
//@RequestMapping("/students")
public class StudentController {
	
	private StudentService service;
	
	public StudentController(StudentService service)
	{
		this.service = service;
	}

	// handler method to handle list of students
	
	@GetMapping("/students") // http://localhost:8080/students
	public String getAllStudentsMethod(Model model)
	{
		List<StudentDto> students = service.getAllStudents();
		
		model.addAttribute("students", students);
		
		return "students";
	}
	
	
	// handler method to handle new student request
	
	@GetMapping("students/new")
	public String newStudent(Model model)
	{
		// Create student model object (StudentDto here) to store student from data
		
		StudentDto studentDto = new StudentDto();
		model.addAttribute("student", studentDto);
		return "create_student";
	}
	
	
	// handler method to handle save student form submit request
	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
								BindingResult result, Model model)
	
	{
		if(result.hasErrors())
		{
			model.addAttribute("student", student);
			return "create_student";
		}
		
		service.createStudent(student);
		return "redirect:/students";
	}
	
	
	// handler method to handle edit student request
	@GetMapping("/students/{studentId}/edit")
	public String editStudent(@PathVariable("studentId") Long studentId, Model model)
	{
		StudentDto student = service.getStudentById(studentId);
		model.addAttribute("student", student);
		return "edit_student";
	}
	
	// handler method to handle edit student form submit request
	@PostMapping("/students/{studentId}")
	public String updateStudent(@PathVariable("studentId") Long studentId, 
								@Valid @ModelAttribute("student") StudentDto studentDto,
								BindingResult result,
								Model model)
	{
		if(result.hasErrors())
		{
			model.addAttribute("student", studentDto);
			return "edit_student";
		}
		studentDto.setId(studentId);
		service.updateStudent(studentDto);
		
		return "redirect:/students";
	}
	
	// Handler method to handle delete student request
	@GetMapping("/students/{studentId}/delete")
	public String deleteStudent(@PathVariable("studentId") Long studentId)
	{
		service.deleteStudent(studentId);
		
		return "redirect:/students";
	}
	
	
	// handler method to handle view student request
	@GetMapping("/students/{studentId}/view")
	public String viewStudent(@PathVariable("studentId") Long studentId,
								Model model)
	{
		StudentDto studentDto = service.getStudentById(studentId);
		model.addAttribute("student", studentDto);
		return "view_student";
	}
	
}
