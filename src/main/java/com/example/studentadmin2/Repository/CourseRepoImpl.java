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
        String sql = "INSERT INTO course (course_id, course_title) VALUES(?,?)";
        template.update(sql, course.getCourse_id(), course.getCourse_title());
        return course;
    }

    @Override
    public Course update(Course course) {
        String sql = "UPDATE course SET course_title=? WHERE course_id=?";
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

    public List<Course> coursesWithExercise(int exercise_id) {
        String sql = "SELECT course.course_id, course.course_title FROM course_exercise JOIN course ON course_exercise.course_course_id = course.course_id WHERE  course_exercise.exercise_exercise_id = ?";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return template.query(sql, rowMapper, exercise_id);
    }

    public boolean exerciseAddCourse(int exercise_id, int course_id) {
        String sql = "INSERT INTO course_exercise (exercise_exercise_id, course_course_id) VALUES(?,?)";
        template.update(sql, exercise_id, course_id);
        return true;
    }

    public boolean exerciseDeleteCourse(int exercise_id, int course_id) {
        String sql = "DELETE FROM course_exercise WHERE exercise_exercise_id = ? AND course_course_id = ?;";
        template.update(sql, exercise_id, course_id);
        return true;
    }

    public List<Course> coursesWithExam(int exam_id) {
        String sql = "SELECT course.course_id, course.course_title FROM course_exam JOIN course ON course_exam.course_course_id = course.course_id WHERE  course_exam.exam_exam_id = ?";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return template.query(sql, rowMapper, exam_id);
    }

    public boolean examAddCourse(int exam_id, int course_id) {
        String sql = "INSERT INTO course_exam (exam_exam_id, course_course_id) VALUES(?,?)";
        template.update(sql, exam_id, course_id);
        return true;
    }

    public boolean examDeleteCourse(int exam_id, int course_id) {
        String sql = "DELETE FROM course_exam WHERE exam_exam_id = ? AND course_course_id = ?;";
        template.update(sql, exam_id, course_id);
        return true;
    }

    public List<Course> coursesWithTeacher(int teacher_id) {
        String sql = "SELECT course.course_id, course.course_title FROM course_teacher JOIN course ON course_teacher.course_course_id = course.course_id WHERE  course_teacher.teacher_teacher_id = ?";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return template.query(sql, rowMapper, teacher_id);
    }

    public boolean teacherAddCourse(int teacher_id, int course_id) {
        String sql = "INSERT INTO course_teacher (teacher_teacher_id, course_course_id) VALUES(?,?)";
        template.update(sql, teacher_id, course_id);
        return true;
    }

    public boolean teacherDeleteCourse(int teacher_id, int course_id) {
        String sql = "DELETE FROM course_teacher WHERE teacher_teacher_id = ? AND course_course_id = ?;";
        template.update(sql, teacher_id, course_id);
        return true;
    }

    public List<Course> coursesWithStudent(int student_id) {
        String sql = "SELECT course.course_id, course.course_title FROM course_student JOIN course ON course_student.course_course_id = course.course_id WHERE  course_student.student_student_id = ?";
        RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
        return template.query(sql, rowMapper, student_id);
    }

    public boolean studentAddCourse(int student_id, int course_id) {
        String sql = "INSERT INTO course_student (student_student_id, course_course_id) VALUES(?,?)";
        template.update(sql, student_id, course_id);
        return true;
    }

    public boolean studentDeleteCourse(int student_id, int course_id) {
        String sql = "DELETE FROM course_student WHERE student_student_id = ? AND course_course_id = ?;";
        template.update(sql, student_id, course_id);
        return true;
    }

}
