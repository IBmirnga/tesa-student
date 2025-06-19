package com.example.tesaStudentIBMirnga.repository.database.interfaces;

import com.example.tesaStudentIBMirnga.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void createStudent(Student student);
    List<Student> findAllStudent();
    Optional<Student> findStudentById(int id);
    List<Student> findStudentByFirstName(String firstName);
    void updateStudent(Student student);
    void deleteStudentById(int id);
    boolean existsById(int id);
}
