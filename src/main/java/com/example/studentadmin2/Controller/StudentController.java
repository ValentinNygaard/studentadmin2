package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.Course;
import com.example.studentadmin2.Model.Exam;
import com.example.studentadmin2.Model.Student;
import com.example.studentadmin2.Service.CourseServiceImpl;
import com.example.studentadmin2.Service.ExamServiceImpl;
import com.example.studentadmin2.Service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class StudentController {


    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    ExamServiceImpl examService;

    private List<Student> searchResult;
    private List<Student> studentList;
    private int currentStudent;

    @GetMapping("/students")
    public String students(Model model){
        studentList = studentService.findAll();
        model.addAttribute("studentList", studentList);
        return "students/students";
    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute Student student){
        studentService.create(student);
        System.out.println(studentService.lastId());
        currentStudent = studentService.lastId();
        courseService.autoCoursesForStudent(currentStudent);
        examService.autoExamsForStudent(currentStudent);
        return "redirect:/students";
    }

    @PostMapping("/searchStudent")
    public String searchStudent(@ModelAttribute Student student){
        searchResult = studentService.searchByName(student.getStudent_name());
        if (searchResult.size()==0) {
            return "redirect:/student-nosearchresult";
        }
        return "redirect:/student-searchresult";
    }

    @GetMapping("/student-searchresult")
    public String StudentSearchResult(Model model){
        model.addAttribute("searchResult", searchResult);
        return "students/student-searchresult";
    }

    @GetMapping("/student-nosearchresult")
    public String studentNoResult(Model model){
        System.out.println(studentList.size());
        studentList = studentService.findAll();
        System.out.println(studentList.size());
        model.addAttribute("studentList", studentList);
        return "students/student-nosearchresult";
    }

    @GetMapping("/student-view/{id}")
    public String studentView(@PathVariable("id") int id, Model model) {
        Student student = studentService.findById(id);
        currentStudent = student.getStudent_id();
        List<Course> coursesWithStudent = courseService.coursesWithStudent(id);
        List<Course> coursesWithoutStudent = courseService.coursesWithoutStudent(id);
        List<Exam> examsWithStudent = examService.examsWithStudent(id);
        List<Exam> examsWithoutStudent = examService.examsWithoutStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("coursesWithStudent", coursesWithStudent);
        model.addAttribute("coursesWithoutStudent", coursesWithoutStudent);
        model.addAttribute("examsWithStudent", examsWithStudent);
        model.addAttribute("examsWithoutStudent", examsWithoutStudent);
        return "students/student-view";
    }

    @GetMapping("/student-addcourse/{id}")
    public String studentAddCourse(@PathVariable("id") int id) {
        courseService.studentAddCourse(currentStudent, id);
        return "redirect:/student-view/"+currentStudent;
    }

    @GetMapping("/student-deletecourse/{id}")
    public String studentDeleteCourse(@PathVariable("id") int id) {
        courseService.studentDeleteCourse(currentStudent, id);
        return "redirect:/student-view/"+currentStudent;
    }

    @GetMapping("/student-addexam/{id}")
    public String studentAddExam(@PathVariable("id") int id) {
        examService.studentAddExam(currentStudent, id);
        return "redirect:/student-view/"+currentStudent;
    }

    @GetMapping("/student-deleteexam/{id}")
    public String studentDeleteExam(@PathVariable("id") int id) {
        examService.studentDeleteExam(currentStudent, id);
        return "redirect:/student-view/"+currentStudent;
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student){
        studentService.update(student);
        return "redirect:/student-view/"+student.getStudent_id();
    }

    @PostMapping("/deleteStudent")
    public String deleteStudent(@ModelAttribute Student student){
        studentService.delete(student.getStudent_id());
        return "redirect:/students";
    }

}
