package com.example.studentadmin2.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Exam {

    @Id
    private int exam_id;
    private String exam_title;
    private String exam_type;

    public Exam(int exam_id, String exam_title, String exam_type) {
        this.exam_id = exam_id;
        this.exam_title = exam_title;
        this.exam_type = exam_type;
    }

    public Exam() {}

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_title() {
        return exam_title;
    }

    public void setExam_title(String exam_title) {
        this.exam_title = exam_title;
    }

    public String getExam_type() {
        return exam_type;
    }

    public void setExam_type(String exam_type) {
        this.exam_type = exam_type;
    }

}
