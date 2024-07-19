package com.example.SpringProject3.service;

import com.example.SpringProject3.dto.StudentDTO;
import com.example.SpringProject3.model.Student;
import com.example.SpringProject3.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Custom exception for duplicate student ID
    public static class DuplicateStudentIdException extends RuntimeException {
        public DuplicateStudentIdException(String message) {
            super(message);
        }
    }

    // Create Student Service function with duplicate ID check
    public StudentDTO saveStudent(StudentDTO studentDTO) {

        Student student = modelMapper.map(studentDTO, Student.class);

        // Check if a student with the same ID already exists
        Optional<Student> existingStudent = studentRepository.findById(student.getId());
        if (existingStudent.isPresent()) {
            throw new DuplicateStudentIdException("Student with ID " + student.getId() + " already exists.");
        }

        // Save the student entity
        studentRepository.save(student);

        return studentDTO;
    }

    // Read Student Service function
    public List<StudentDTO> getAllStudent() {
        List<Student> studentList = studentRepository.findAll();
        return modelMapper.map(studentList, new TypeToken<List<StudentDTO>>(){}.getType());
    }

    // Update Student Service function
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        studentRepository.save(modelMapper.map(studentDTO, Student.class));
        return studentDTO;
    }

    // Delete Student Service function
    public StudentDTO deleteStudent(StudentDTO studentDTO) {
        studentRepository.delete(modelMapper.map(studentDTO, Student.class));
        return studentDTO;
    }

    // Find Student By ID
    public StudentDTO getStudentByStudentId(String id) {
        Student student = studentRepository.getStudentByStudentId(id);
        return modelMapper.map(student, StudentDTO.class);
    }
}
