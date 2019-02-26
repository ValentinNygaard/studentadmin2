package com.example.studentadmin2.Service;

import com.example.studentadmin2.Model.Student;
import com.example.studentadmin2.Repository.StudentRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements IService<Student> {

    @Autowired
    StudentRepoImpl repoImpl;

    @Override
    public List<Student> findAll() {
        return repoImpl.findAll();
    }

    @Override
    public Student findById(int id) {
        return repoImpl.findById(id);
    }

    @Override
    public Student create(Student student) {
        return repoImpl.create(student);
    }

    @Override
    public Student update(Student student) {
        return repoImpl.update(student);
    }

    @Override
    public boolean delete(int id) { return repoImpl.delete(id); }

    public List<Student> searchByName(String student_name) { return repoImpl.searchByName(student_name); }

    public List<Student> studentsWithExam(int exam_id) { return repoImpl.studentsWithExam(exam_id); }

    public List<Student> studentsWithCourse(int course_id) { return repoImpl.studentsWithCourse(course_id); }

    public int lastId () { return repoImpl.lastId(); }

}
