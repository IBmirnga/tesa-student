package com.example.tesaStudentIBMirnga.repository.database.implementation;

import com.example.tesaStudentIBMirnga.model.entity.Student;
import com.example.tesaStudentIBMirnga.repository.database.interfaces.StudentRepository;
import com.example.tesaStudentIBMirnga.repository.database.query.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StudentRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createStudent(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentFirstName", student.getStudentFirstName())
                .addValue("studentLastName", student.getStudentLastName())
                .addValue("studentStateOfOrigin", student.getStudentStateOfOrigin())
                .addValue("studentAge", student.getStudentAge())
                .addValue("studentStatus", student.getStudentStatus());
        jdbcTemplate.update(StudentQuery.INSERT_STUDENT, params);
    }

    @Override
    public List<Student> findAllStudent() {
        return jdbcTemplate.query(StudentQuery.FIND_ALL_STUDENT, new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public Optional<Student> findStudentById(int id) {
        try {
            Student student = jdbcTemplate.queryForObject(
                    StudentQuery.FIND_STUDENT_BY_ID,
                    new MapSqlParameterSource("studentId", id),
                    new BeanPropertyRowMapper<>(Student.class));
            return Optional.ofNullable(student);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findStudentByFirstName(String firstName) {
        return jdbcTemplate.query(
                StudentQuery.FIND_STUDENT_BY_FIRSTNAME,
                new MapSqlParameterSource("studentFirstName", firstName),
                new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public List<Student> studentSearch(String query) {
        return jdbcTemplate.query(
                StudentQuery.SEARCH_STUDENT,
                new MapSqlParameterSource("query", "%" + query + "%"),
                new BeanPropertyRowMapper<>(Student.class));
    }

    @Override
    public void updateStudent(Student student) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("studentId", student.getStudentId())
                .addValue("studentFirstName", student.getStudentFirstName())
                .addValue("studentLastName", student.getStudentLastName())
                .addValue("studentStateOfOrigin", student.getStudentStateOfOrigin())
                .addValue("studentAge", student.getStudentAge())
                .addValue("studentStatus", student.getStudentStatus());
        jdbcTemplate.update(StudentQuery.UPDATE_STUDENT, params);
    }

    @Override
    public void deleteStudentById(int id) {
        jdbcTemplate.update(StudentQuery.DELETE_STUDENT, new MapSqlParameterSource("studentId", id));
    }

    @Override
    public boolean existsById(int id) {
        Integer count = jdbcTemplate.queryForObject(
                StudentQuery.EXISTS_BY_ID,
                new MapSqlParameterSource("studentId", id),
                Integer.class);
        return count != null && count > 0;
    }
}
