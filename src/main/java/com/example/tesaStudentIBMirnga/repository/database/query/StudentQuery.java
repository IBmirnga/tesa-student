package com.example.tesaStudentIBMirnga.repository.database.query;


public class StudentQuery {

    public static final String INSERT_STUDENT = """
        INSERT INTO TESA_IBMOHAMMED_STUDENT (studentFirstName, studentLastName, studentStateOfOrigin, studentAge)
        VALUES (:studentFirstName, :studentLastName, :studentStateOfOrigin, :studentAge)""";


    public static final String FIND_ALL_STUDENT = """
        SELECT * FROM TESA_IBMOHAMMED_STUDENT""";

    public static final String FIND_STUDENT_BY_ID = """
        SELECT * FROM TESA_IBMOHAMMED_STUDENT WHERE studentId = :studentId""";

    public static final String FIND_STUDENT_BY_FIRSTNAME = """
        SELECT * FROM TESA_IBMOHAMMED_STUDENT WHERE studentFirstName = :studentFirstName""";

    public static final String UPDATE_STUDENT = """
        UPDATE TESA_IBMOHAMMED_STUDENT
        SET studentFirstName = :studentFirstName,
            studentLastName = :studentLastName,
            studentStateOfOrigin = :studentStateOfOrigin,
            studentAge = :studentAge,
            studentStatus = :studentStatus
        WHERE studentId = :studentId""";

    public static final String DELETE_STUDENT = """
        DELETE FROM TESA_IBMOHAMMED_STUDENT WHERE studentId = :studentId""";

    public static final String EXISTS_BY_ID = """
        SELECT COUNT(*) FROM TESA_IBMOHAMMED_STUDENT WHERE studentId = :studentId""";
}