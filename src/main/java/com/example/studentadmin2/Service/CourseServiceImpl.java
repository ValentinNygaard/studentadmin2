package com.example.studentadmin2.Service;

import com.example.studentadmin2.Model.Course;
import com.example.studentadmin2.Repository.CourseRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements IService<Course> {

    @Autowired
    CourseRepoImpl repoImpl;

    @Override
    public List<Course> findAll() {
        return repoImpl.findAll();
    }

    @Override
    public Course findById(int id) {
        return repoImpl.findById(id);
    }

    @Override
    public Course create(Course course) {
        return repoImpl.create(course);
    }

    @Override
    public Course update(Course course) {
        return repoImpl.update(course);
    }

    @Override
    public boolean delete(int id) { return repoImpl.delete(id); }

}