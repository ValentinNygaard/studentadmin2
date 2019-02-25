package com.example.studentadmin2.Repository;

import com.example.studentadmin2.Model.Course;
import com.example.studentadmin2.Model.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExamRepoImpl implements IRepo<Exam> {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Exam> findAll() {
        String sql = "SELECT * FROM exam";
        RowMapper<Exam> rowMapper = new BeanPropertyRowMapper<>(Exam.class);
        return template.query(sql, rowMapper);
    }

    @Override
    public Exam findById(int id) {
        String sql ="SELECT * FROM exam WHERE exam_id=?";
        RowMapper<Exam> rowMapper = new BeanPropertyRowMapper<>(Exam.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Exam create(Exam exam) {
        String sql = "INSERT INTO exam (exam_id, exam_title, exam_type) VALUES(?,?,?)";
        template.update(sql, exam.getExam_id(), exam.getExam_title(), exam.getExam_type());
        return exam;
    }

    @Override
    public Exam update(Exam exam) {
        String sql = "UPDATE exam SET exam_title=?, exam_type=? WHERE exam_id=?";
        template.update(sql,exam.getExam_title(), exam.getExam_type(), exam.getExam_id());
        return exam;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM exam WHERE exam_id=?";
        return template.update(sql, id) >= 0;
    }

    public List<Exam> searchByName(String exam_title) {
        String sql ="SELECT * FROM exam WHERE exam_title LIKE ?";
        RowMapper<Exam> rowMapper = new BeanPropertyRowMapper<>(Exam.class);
        return template.query(sql, rowMapper, exam_title);
    }


    public List<Exam> examsWithStudent(int student_id) {
        String sql = "SELECT exam.exam_id, exam.exam_title FROM exam_student JOIN exam ON exam_student.exam_exam_id = exam.exam_id WHERE  exam_student.student_student_id = ?";
        RowMapper<Exam> rowMapper = new BeanPropertyRowMapper<>(Exam.class);
        return template.query(sql, rowMapper, student_id);
    }

    public boolean studentAddExam(int student_id, int exam_id) {
        String sql = "INSERT INTO exam_student (student_student_id, exam_exam_id) VALUES(?,?)";
        template.update(sql, student_id, exam_id);
        return true;
    }

    public boolean studentDeleteExam(int student_id, int exam_id) {
        String sql = "DELETE FROM exam_student WHERE student_student_id = ? AND exam_exam_id = ?;";
        template.update(sql, student_id, exam_id);
        return true;
    }
}
