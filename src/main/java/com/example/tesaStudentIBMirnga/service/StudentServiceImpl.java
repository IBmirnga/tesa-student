package com.example.tesaStudentIBMirnga.service;

import com.example.tesaStudentIBMirnga.model.entity.Student;
import com.example.tesaStudentIBMirnga.model.request.StudentRequest;
import com.example.tesaStudentIBMirnga.model.response.StudentResponse;
import com.example.tesaStudentIBMirnga.repository.database.interfaces.StudentRepository;
import com.example.tesaStudentIBMirnga.utils.StudentUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    Gson gson = new Gson();

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse createStudent(StudentRequest request) {

        try {
            var student = gson.fromJson(gson.toJson(request), Student.class);

            System.out.println("STUDENT NAME >>");
            System.out.println(student.getStudentFirstName());

            System.out.println("STUDENT Id >>");
            System.out.println(student.getStudentId());

            studentRepository.createStudent(student);

            return StudentResponse.builder()
                    .responseCode(StudentUtils.SUCCESS)
                    .responseMessage(StudentUtils.SUCCESS_MESSAGE)
                    .request(StudentRequest.builder()
                            .studentFirstName(request.getStudentFirstName())
                            .studentLastName(request.getStudentLastName())
                            .studentStateOfOrigin(request.getStudentStateOfOrigin())
                            .studentAge(request.getStudentAge())
                            .studentStatus(request.getStudentStatus())
                            .build())
                    .build();
        } catch (DataAccessException e) {
            //throw new RuntimeException("Failed to create student: " + e.getMessage(), e);
            return StudentResponse.builder()
                    .responseCode(StudentUtils.ERROR)
                    .responseMessage(StudentUtils.ERROR_MESSAGE)
                    .build();
        }
    }

    @Override
    public List<Student> getAllStudent() {
        try {
            return studentRepository.findAllStudent();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get students: " + e.getMessage(), e);
        }
    }

    @Override
    public Student getStudentById(int id) {
        try {
            return studentRepository.findStudentById(id)
                    .orElseThrow(() -> new NoSuchElementException("Student  with id: " + id + " not found"));
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get student: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Student> getStudentByFirstName(String firstName) {
        try {
            return studentRepository.findStudentByFirstName(firstName);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to get student: " + e.getMessage(), e);
        }
    }

    @Override
    public StudentResponse updateStudent(int id, StudentRequest request) {
        try {
            Student existingStudent = studentRepository.findStudentById(id)
                    .orElseThrow(() -> new NoSuchElementException("Student  with id: " + id + " not found"));

            Student updatedStudent = gson.fromJson(gson.toJson(request), Student.class);
            updatedStudent.setStudentId(id);
            studentRepository.updateStudent(updatedStudent);

            return StudentResponse.builder()
                    .responseCode(StudentUtils.SUCCESS)
                    .responseMessage(StudentUtils.SUCCESS_MESSAGE)
                    .request(StudentRequest.builder()
                            .studentFirstName(request.getStudentFirstName())
                            .studentLastName(request.getStudentLastName())
                            .studentStateOfOrigin(request.getStudentStateOfOrigin())
                            .studentAge(request.getStudentAge())
                            .studentStatus(request.getStudentStatus())
                            .build())
                    .build();
        } catch (DataAccessException e) {
            //throw new RuntimeException("Failed to update student: " + e.getMessage(), e);
            return StudentResponse.builder()
                    .responseCode(StudentUtils.ERROR)
                    .responseMessage(StudentUtils.ERROR_MESSAGE)
                    .build();
        }
    }

    @Override
    public StudentResponse deleteStudent(int id) {
        try {
            if (!studentRepository.existsById(id)) {
                throw new NoSuchElementException("Student  with id: " + id + " not found");
            }
            studentRepository.deleteStudentById(id);

            return StudentResponse.builder()
                    .responseCode(StudentUtils.SUCCESS)
                    .responseMessage(StudentUtils.SUCCESS_MESSAGE)
                    .build();
        } catch (DataAccessException e) {
            //throw new RuntimeException("Failed to delete student: " + e.getMessage(), e);
            return StudentResponse.builder()
                    .responseCode(StudentUtils.ERROR)
                    .responseMessage(StudentUtils.ERROR_MESSAGE)
                    .build();
        }
    }


}