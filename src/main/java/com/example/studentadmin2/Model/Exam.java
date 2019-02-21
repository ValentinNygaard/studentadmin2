package com.example.studentadmin2.Model;

public class Exam {

    private int exam_id;
    private String exam_title;
    private String exam_place;

    public Exam(int exam_id, String exam_title, String exam_place) {
        this.exam_id = exam_id;
        this.exam_title = exam_title;
        this.exam_place = exam_place;
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

    public String getExam_place() {
        return exam_place;
    }

    public void setExam_place(String exam_place) {
        this.exam_place = exam_place;
    }
}
