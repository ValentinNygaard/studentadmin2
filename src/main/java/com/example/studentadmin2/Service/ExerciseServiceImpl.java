package com.example.studentadmin2.Service;

import com.example.studentadmin2.Model.Exercise;
import com.example.studentadmin2.Repository.ExerciseRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseServiceImpl implements IService<Exercise> {

    @Autowired
    ExerciseRepoImpl repoImpl;

    @Override
    public List<Exercise> findAll() {
        return repoImpl.findAll();
    }

    @Override
    public Exercise findById(int id) {
        return repoImpl.findById(id);
    }

    @Override
    public Exercise create(Exercise exercise) {
        return repoImpl.create(exercise);
    }

    @Override
    public Exercise update(Exercise exercise) {
        return repoImpl.update(exercise);
    }

    @Override
    public boolean delete(int id) { return repoImpl.delete(id); }

    public List<Exercise> searchByName(String exercise_title) { return repoImpl.searchByName(exercise_title); }

    public List<Exercise> exercisesWithCourse(int course_id) { return repoImpl.exercisesWithCourse(course_id); }

}