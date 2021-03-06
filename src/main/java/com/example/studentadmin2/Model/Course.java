package com.example.studentadmin2.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    private int course_id;
    private String course_title;

    public Course(int course_id, String course_title) {
        this.course_id = course_id;
        this.course_title = course_title;
    }

    public Course() {}

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public boolean equals(Course obj) {
        return (this.course_id == obj.course_id);
    }

}
