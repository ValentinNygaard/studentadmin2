package com.example.studentadmin2.Repository;

import com.example.studentadmin2.Model.Student;
import com.example.studentadmin2.Model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepoImpl implements IRepo<Teacher> {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Teacher> findAll() {
        String sql = "SELECT * FROM teacher";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        return template.query(sql, rowMapper);
    }

    @Override
    public Teacher findById(int id) {
        String sql ="SELECT * FROM teacher WHERE teacher_id=?";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Teacher create(Teacher teacher) {
        String sql = "INSERT INTO teacher (teacher_id, teacher_name) VALUES(?,?)";
        template.update(sql, teacher.getTeacher_id(), teacher.getTeacher_name());
        return teacher;
    }

    @Override
    public Teacher update(Teacher teacher) {
        String sql = "UPDATE teacher SET teacher_name=? WHERE teacher_id=?";
        template.update(sql,teacher.getTeacher_name(), teacher.getTeacher_id());
        return teacher;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM teacher WHERE teacher_id=?";
        return template.update(sql, id) >= 0;
    }

    public List<Teacher> searchByName(String teacher_name) {
        String sql ="SELECT * FROM teacher WHERE teacher_name LIKE ?";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        return template.query(sql, rowMapper, teacher_name);
    }

    public List<Teacher> teachersWithExam(int exam_id) {
        String sql ="SELECT teacher_id, teacher_name FROM teacher JOIN course_teacher ON course_teacher.teacher_teacher_id = teacher.teacher_id JOIN course_exam ON course_teacher.course_course_id = course_exam.course_course_id WHERE course_exam.exam_exam_id = ?;";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        return template.query(sql, rowMapper, exam_id);
    }

    public List<Teacher> teachersWithCourse(int course_id) {
        String sql ="SELECT teacher.teacher_id, teacher.teacher_name FROM course_teacher JOIN teacher ON course_teacher.teacher_teacher_id = teacher.teacher_id WHERE  course_teacher.course_course_id = ?;";
        RowMapper<Teacher> rowMapper = new BeanPropertyRowMapper<>(Teacher.class);
        return template.query(sql, rowMapper, course_id);
    }
}
