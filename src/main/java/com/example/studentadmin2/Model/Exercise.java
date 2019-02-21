package com.example.studentadmin2.Model;

public class Exercise {

    private int exercise_id;
    private String exercise_title;

    public Exercise(int exercise_id, String exercise_title) {
        this.exercise_id = exercise_id;
        this.exercise_title = exercise_title;
    }

    public Exercise() {}

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_title() {
        return exercise_title;
    }

    public void setExercise_title(String exercise_title) {
        this.exercise_title = exercise_title;
    }
}
