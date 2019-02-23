package com.example.studentadmin2.Repository;

import com.example.studentadmin2.Model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExerciseRepoImpl implements IRepo<Exercise> {

    @Autowired
    JdbcTemplate template;

    @Override
    public List<Exercise> findAll() {
        String sql = "SELECT * FROM exercise";
        RowMapper<Exercise> rowMapper = new BeanPropertyRowMapper<>(Exercise.class);
        return template.query(sql, rowMapper);
    }

    @Override
    public Exercise findById(int id) {
        String sql ="SELECT * FROM exercise WHERE exercise_id=?";
        RowMapper<Exercise> rowMapper = new BeanPropertyRowMapper<>(Exercise.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public Exercise create(Exercise exercise) {
        String sql = "INSERT INTO exercise (exercise_id, exercise_name) VALUES(?,?)";
        template.update(sql, exercise.getExercise_id(), exercise.getExercise_title());
        return exercise;
    }

    @Override
    public Exercise update(Exercise exercise) {
        String sql = "UPDATE exercise SET exercise_name=?, WHERE exercise_id=?";
        template.update(sql,exercise.getExercise_title(), exercise.getExercise_id());
        return exercise;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM exercise WHERE exercise_id=?";
        return template.update(sql, id) >= 0;
    }

}