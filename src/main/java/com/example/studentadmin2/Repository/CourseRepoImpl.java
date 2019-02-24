package com.example.studentadmin2.Repository;

import com.example.studentadmin2.Model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepoImpl implements IRepo<Course> {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Course> findAll() {
        String sql = "SELECT * FROM course";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return template.query(sql, rowMapper);
    }

    @Override
    public Course findById(int id) {
        String sql ="SELECT * FROM course WHERE course_id=?";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Course create(Course course) {
        String sql = "INSERT INTO course (course_id, course_name) VALUES(?,?)";
        template.update(sql, course.getCourse_id(), course.getCourse_title());
        return course;
    }

    @Override
    public Course update(Course course) {
        String sql = "UPDATE course SET course_name=?, WHERE course_id=?";
        template.update(sql,course.getCourse_title(), course.getCourse_id());
        return course;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM course WHERE course_id=?";
        return template.update(sql, id) >= 0;
    }

    public List<Course> searchByName(String course_title) {
        String sql ="SELECT * FROM course WHERE course_title LIKE ?";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return template.query(sql, rowMapper, course_title);
    }

}
