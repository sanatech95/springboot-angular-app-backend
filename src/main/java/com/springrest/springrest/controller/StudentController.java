package com.springrest.springrest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.exception.ResourceNotFoundException;
import com.springrest.springrest.model.Student;
import com.springrest.springrest.repository.StudentRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	//get all students
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	
	//create student REST API
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		System.out.println(" line no 41" +student);
		return studentRepository.save(student);
	}
	
	//get student by Id REST API
		@GetMapping("/students/{id}")
		public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
			Student student = studentRepository.findById(id).orElseThrow(() ->
			              new ResourceNotFoundException("Student not exist with id :" +id));	
			return ResponseEntity.ok(student);
		}
		
		//update student REST API
		@PutMapping("/students/{id}")
		public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
			
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" +id));
			
			student.setFirstName(studentDetails.getFirstName());
			student.setLastName(studentDetails.getLastName());
			student.setEmailId(studentDetails.getEmailId());
			student.setMobileNo(studentDetails.getMobileNo());
			
			Student updateStudent = studentRepository.save(student);
			return ResponseEntity.ok(updateStudent);
		}
		
		//delete student REST API
		@DeleteMapping("/students/{id}")
	 	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id){ 
			Student student = studentRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" +id));
			
			studentRepository.delete(student);
			Map<String, Boolean>response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return  ResponseEntity.ok(response);
			
		}
}
