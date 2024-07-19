package com.example.SpringProject3.repository;

import com.example.SpringProject3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student,Integer> {


    @Query(value = "SELECT * FROM Student WHERE ID = ?1",nativeQuery = true)
    Student getStudentByStudentId(String ID);

    Optional<Student> findById(String id);
}
