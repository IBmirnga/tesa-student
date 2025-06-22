package com.example.tesaStudentIBMirnga.service;

import com.example.tesaStudentIBMirnga.model.entity.Student;
import com.example.tesaStudentIBMirnga.model.request.StudentRequest;
import com.example.tesaStudentIBMirnga.model.response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse createStudent(StudentRequest request);
    List<Student> getAllStudent();
    Student getStudentById(int id);
    List<Student> getStudentByFirstName(String firstName);
    List<Student> searchStudent(String query);
    StudentResponse updateStudent(int id, StudentRequest request);
    StudentResponse deleteStudent(int id);

}
