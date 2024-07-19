package com.example.SpringProject3.controller;

import com.example.SpringProject3.dto.StudentDTO;
import com.example.SpringProject3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/student")
@CrossOrigin

public class StudentController {
 @Autowired
    private StudentService studentService;

 //Get All Students
 @GetMapping("/getStudents")
    public List<StudentDTO> getStudent(){
     return studentService.getAllStudent();
 }

 //Save Student
 @PostMapping("/saveStudent")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO){
     return studentService.saveStudent(studentDTO);
 }

 //Update Student
 @PutMapping("updateStudent")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO){
     return studentService.updateStudent(studentDTO);
 }

 //Delete Student
    @DeleteMapping("deleteStudent")
    public StudentDTO deleteStudent(@RequestBody StudentDTO studentDTO){
     return studentService.deleteStudent(studentDTO);
    }

    //Find Student By ID
    @GetMapping("/getStudentById/{studentId}")
    public StudentDTO getStudentByStudentI(@PathVariable String studentId){
     return studentService.getStudentByStudentId(studentId);
    }
}


