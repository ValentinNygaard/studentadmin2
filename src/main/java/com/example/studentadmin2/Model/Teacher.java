package com.example.studentadmin2.Model;

public class Teacher {

    private int teacher_id;
    private String teacher_name;

    public Teacher(int teacher_id, String teacher_name) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
    }

    public Teacher() {}

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
