package com.example.tesaStudentIBMirnga.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequest {
    private String studentFirstName;
    private String studentLastName;
    private String studentStateOfOrigin;
    private int studentAge;
    private String studentStatus;
}
