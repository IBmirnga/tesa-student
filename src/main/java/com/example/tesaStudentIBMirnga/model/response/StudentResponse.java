package com.example.tesaStudentIBMirnga.model.response;

import com.example.tesaStudentIBMirnga.model.request.StudentRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private String responseCode;
    private String responseMessage;
    private StudentRequest request;
}
