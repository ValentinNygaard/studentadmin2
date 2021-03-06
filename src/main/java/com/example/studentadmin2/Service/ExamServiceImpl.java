package com.example.studentadmin2.Service;

import com.example.studentadmin2.Model.Exam;
import com.example.studentadmin2.Repository.ExamRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements IService<Exam> {

    @Autowired
    ExamRepoImpl repoImpl;

    @Override
    public List<Exam> findAll() {
        return repoImpl.findAll();
    }

    @Override
    public Exam findById(int id) {
        return repoImpl.findById(id);
    }

    @Override
    public Exam create(Exam exam) {
        return repoImpl.create(exam);
    }

    @Override
    public Exam update(Exam exam) {
        return repoImpl.update(exam);
    }

    @Override
    public boolean delete(int id) { return repoImpl.delete(id); }

    public List<Exam> searchByName(String exam_title) { return repoImpl.searchByName(exam_title); }

    // Operations on exam_student table

    public boolean studentAddExam(int student_id, int exam_id) { return repoImpl.studentAddExam(student_id, exam_id); }

    public boolean studentDeleteExam(int student_id, int exam_id) { return repoImpl.studentDeleteExam(student_id, exam_id); }

    public List<Exam> examsWithStudent(int student_id) { return repoImpl.examsWithStudent(student_id); }

    public List<Exam> examsWithoutStudent(int student_id) { //Could be made generic to avoid repetition of code

        List<Exam> listA = new ArrayList<>(findAll());
        List<Exam> listB = new ArrayList<>(examsWithStudent(student_id));

        for (Exam aListB : listB) {
            for (int j = 0; j < listA.size(); j++) {
                if (listA.get(j).getExam_id() == aListB.getExam_id()) {
                    listA.remove(j);
                    j--;
                }
            }
        }
        return listA;
    }

    public List<Exam> examsWithCourse(int course_id) { return repoImpl.examsWithCourse(course_id); }

    //adding of all exams to students - missing check of relations exists - should only be used with just created students

    public boolean autoExamsForStudent(int student_id) {

        List<Exam> examlist = new ArrayList<>(findAll());

        for (int c = 0; c < examlist.size(); c++) {
            repoImpl.studentAddExam(student_id, examlist.get(c).getExam_id());
        }
        return true;
    }

}