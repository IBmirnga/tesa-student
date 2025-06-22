package com.example.tesaStudentIBMirnga.controller;

import com.example.tesaStudentIBMirnga.model.entity.Student;
import com.example.tesaStudentIBMirnga.model.request.StudentRequest;
import com.example.tesaStudentIBMirnga.model.response.StudentResponse;
import com.example.tesaStudentIBMirnga.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create-student")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
            StudentResponse response = studentService.createStudent(request);
            return ResponseEntity.ok().body(response);

    }

//    public ResponseEntity<String> createStudent(@RequestBody StudentRequest request) {
//        try {
//            studentService.createStudent(request);
//            return ResponseEntity.ok().body("Student created successfully");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error creating student: " + e.getMessage());
//        }
//    }

    @GetMapping("/all-students")
    public ResponseEntity<?> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudent();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting student: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.ok(student);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting student: " + e.getMessage());
        }
    }

    @GetMapping("/by-firstname")
    public ResponseEntity<?> getStudentByFirstName(@RequestParam String firstName) {
        try {
            List<Student> students = studentService.getStudentByFirstName(firstName);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting students by first name: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchStudent(@RequestParam String query) {
        try {
            List<Student> students = studentService.searchStudent(query);
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting students by first name: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable int id, @RequestBody StudentRequest request) {

        StudentResponse response = studentService.updateStudent(id, request);
        return ResponseEntity.ok().body(response);
    }

//    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody StudentRequest request) {
//        try {
//            studentService.updateStudent(id, request);
//            return ResponseEntity.ok().body("Student updated successfully");
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error updating student: " + e.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable int id) {

        StudentResponse response =  studentService.deleteStudent(id);
        return ResponseEntity.ok().body(response);
    }

//    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
//        try {
//            studentService.deleteStudent(id);
//            return ResponseEntity.ok().body("Student deleted successfully");
//        } catch (NoSuchElementException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error deleting student: " + e.getMessage());
//        }
//    }
}