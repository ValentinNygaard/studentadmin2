package com.example.studentadmin2.Repository;

import com.example.studentadmin2.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepoImpl implements IRepo<Student> {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM student";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        return template.query(sql, rowMapper);
    }

    @Override
    public Student findById(int id) {
        String sql ="SELECT * FROM student WHERE student_id=?";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Student create(Student student) {
        String sql = "INSERT INTO student (student_id, student_name) VALUES(?,?)";
        template.update(sql, student.getStudent_id(), student.getStudent_name());
        return student;
    }

    @Override
    public Student update(Student student) {
        String sql = "UPDATE student SET student_name=? WHERE student_id=?";
        System.out.println("Name: " + student.getStudent_name());
        System.out.println(student.getStudent_id());
        template.update(sql,student.getStudent_name(), student.getStudent_id());
        return student;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM student WHERE student_id=?";
        return template.update(sql, id) >= 0;
    }

    public List<Student> searchByName(String student_name) {
        String sql ="SELECT * FROM student WHERE student_name LIKE ?";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        return template.query(sql, rowMapper, student_name);
    }

    public List<Student> studentsWithExam(int exam_id) {
        String sql ="SELECT student.student_id, student.student_name FROM exam_student JOIN student ON exam_student.student_student_id = student.student_id WHERE  exam_student.exam_exam_id = ? ORDER BY exam_student.exam_student_id DESC;";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        return template.query(sql, rowMapper, exam_id);
    }

    public List<Student> studentsWithCourse(int course_id) {
        String sql ="SELECT student.student_id, student.student_name FROM course_student JOIN student ON course_student.student_student_id = student.student_id WHERE  course_student.course_course_id = ? ORDER BY student.student_name;";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        return template.query(sql, rowMapper, course_id);
    }

    //get id for new object added to DB - problem with return value from db insert

    public int lastId() {
        String sql ="SELECT LAST_INSERT_ID()";
        Integer id = (template.queryForObject(sql, Integer.class));
        return id.intValue();
    }

}
