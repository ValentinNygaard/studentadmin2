package com.example.studentadmin2.Service;

import com.example.studentadmin2.Model.Course;
import com.example.studentadmin2.Repository.CourseRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Course> searchByName(String course_title) { return repoImpl.searchByName(course_title); }



    public List<Course> coursesWithExercise(int exercise_id) { return repoImpl.coursesWithExercise(exercise_id); }

    public List<Course> coursesWithoutExercise(int exercise_id) {

        List<Course> listA = new ArrayList<>(findAll());
        List<Course> listB = new ArrayList<>(coursesWithExercise(exercise_id));

        for (Course aListB : listB) {
            for (int j = 0; j < listA.size(); j++) {
                if (listA.get(j).getCourse_id() == aListB.getCourse_id()) {
                    listA.remove(j);
                    j--;
                }
            }
        }
        return listA;
    }

    public boolean exerciseAddCourse(int exercise_id, int course_id) { return repoImpl.exerciseAddCourse(exercise_id, course_id); }

    public boolean exerciseDeleteCourse(int exercise_id, int course_id) { return repoImpl.exerciseDeleteCourse(exercise_id, course_id); }


    public boolean examAddCourse(int exam_id, int course_id) { return repoImpl.examAddCourse(exam_id, course_id); }

    public boolean examDeleteCourse(int exam_id, int course_id) { return repoImpl.examDeleteCourse(exam_id, course_id); }

    public List<Course> coursesWithExam(int exam_id) { return repoImpl.coursesWithExam(exam_id); }

    public List<Course> coursesWithoutExam(int exam_id) {

        List<Course> listA = new ArrayList<>(findAll());
        List<Course> listB = new ArrayList<>(coursesWithExam(exam_id));

        for (Course aListB : listB) {
            for (int j = 0; j < listA.size(); j++) {
                if (listA.get(j).getCourse_id() == aListB.getCourse_id()) {
                    listA.remove(j);
                    j--;
                }
            }
        }
        return listA;
    }

    public boolean teacherAddCourse(int teacher_id, int course_id) { return repoImpl.teacherAddCourse(teacher_id, course_id); }

    public boolean teacherDeleteCourse(int exercise_id, int course_id) { return repoImpl.teacherDeleteCourse(exercise_id, course_id); }

    public List<Course> coursesWithTeacher(int teacher_id) { return repoImpl.coursesWithTeacher(teacher_id); }

    public List<Course> coursesWithoutTeacher(int teacher_id) {

        List<Course> listA = new ArrayList<>(findAll());
        List<Course> listB = new ArrayList<>(coursesWithTeacher(teacher_id));

        for (Course aListB : listB) {
            for (int j = 0; j < listA.size(); j++) {
                if (listA.get(j).getCourse_id() == aListB.getCourse_id()) {
                    listA.remove(j);
                    j--;
                }
            }
        }
        return listA;
    }

    public boolean studentAddCourse(int student_id, int course_id) { return repoImpl.studentAddCourse(student_id, course_id); }

    public boolean studentDeleteCourse(int student_id, int course_id) { return repoImpl.studentDeleteCourse(student_id, course_id); }

    public List<Course> coursesWithStudent(int student_id) { return repoImpl.coursesWithStudent(student_id); }

    public List<Course> coursesWithoutStudent(int student_id) {

        List<Course> listA = new ArrayList<>(findAll());
        List<Course> listB = new ArrayList<>(coursesWithStudent(student_id));

        for (Course aListB : listB) {
            for (int j = 0; j < listA.size(); j++) {
                if (listA.get(j).getCourse_id() == aListB.getCourse_id()) {
                    listA.remove(j);
                    j--;
                }
            }
        }
        return listA;
    }

    public boolean autoCoursesForStudent(int student_id) {

        List<Course> courselist = new ArrayList<>(findAll());

        for (int c = 0; c < courselist.size(); c++) {
            System.out.println("s id: "+student_id);
            System.out.println("c id: "+courselist.get(c).getCourse_id());
            repoImpl.studentAddCourse(student_id, courselist.get(c).getCourse_id());
        }
        return true;
    }

}