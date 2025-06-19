package com.example.tesaStudentIBMirnga.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentStateOfOrigin;
    private int studentAge;
    private String studentStatus;
    private String studentCreatedAt;
    private String studentUpdatedAt;
}
