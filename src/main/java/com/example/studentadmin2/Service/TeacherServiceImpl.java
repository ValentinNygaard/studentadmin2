package com.example.studentadmin2.Service;

import com.example.studentadmin2.Model.Teacher;
import com.example.studentadmin2.Repository.TeacherRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements IService<Teacher> {

    @Autowired
    TeacherRepoImpl repoImpl;

    @Override
    public List<Teacher> findAll() {
        return repoImpl.findAll();
    }

    @Override
    public Teacher findById(int id) {
        return repoImpl.findById(id);
    }

    @Override
    public Teacher create(Teacher teacher) {
        return repoImpl.create(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return repoImpl.update(teacher);
    }

    @Override
    public boolean delete(int id) { return repoImpl.delete(id); }

    public List<Teacher> searchByName(String teacher_name) { return repoImpl.searchByName(teacher_name); }

    public List<Teacher> teachersWithExam(int exam_id) { return repoImpl.teachersWithExam(exam_id); }

    public List<Teacher> teachersWithCourse(int course_id) { return repoImpl.teachersWithCourse(course_id); }

}
