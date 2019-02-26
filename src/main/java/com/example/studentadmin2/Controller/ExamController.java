package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.Course;
import com.example.studentadmin2.Model.Exam;
import com.example.studentadmin2.Model.Student;
import com.example.studentadmin2.Model.Teacher;
import com.example.studentadmin2.Service.CourseServiceImpl;
import com.example.studentadmin2.Service.ExamServiceImpl;
import com.example.studentadmin2.Service.StudentServiceImpl;
import com.example.studentadmin2.Service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ExamController {

    @Autowired
    ExamServiceImpl examService;
    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    StudentServiceImpl studentService;

    private List<Exam> searchResult;
    private List<Exam> examList;
    private int currentExam;

    @GetMapping("/exams")
    public String exams(Model model){
        examList = examService.findAll();
        model.addAttribute("examList", examList);
        return "exams/exams";
    }

    @PostMapping("/createExam")
    public String createExam(@ModelAttribute Exam exam){
        examService.create(exam);
        return "redirect:/exams";
    }

    @PostMapping("/searchExam")
    public String searchExam(@ModelAttribute Exam exam){
        searchResult = examService.searchByName(exam.getExam_title());
        if (searchResult.size()==0) {
            return "redirect:/exam-nosearchresult";
        }
        return "redirect:/exam-searchresult";
    }

    @GetMapping("/exam-searchresult")
    public String examSearchResult(Model model){
        model.addAttribute("searchResult", searchResult);
        return "exams/exam-searchresult";
    }

    @GetMapping("/exam-nosearchresult")
    public String examNoResult(Model model){
        examList = examService.findAll();
        model.addAttribute("examList", examList);
        return "exams/exam-nosearchresult";
    }

    @GetMapping("/exam-view/{id}")
    public String examView(@PathVariable("id") int id, Model model) {
        Exam exam = examService.findById(id);
        currentExam = exam.getExam_id();
        List<Course> coursesWithExam = courseService.coursesWithExam(id);
        List<Teacher> teachersWithExam = teacherService.teachersWithExam(id);
        List<Student> studentsWithExam = studentService.studentsWithExam(id);
        List<Course> coursesWithoutExam = courseService.coursesWithoutExam(id);
        model.addAttribute("exam", exam);
        model.addAttribute("coursesWithExam", coursesWithExam);
        model.addAttribute("coursesWithoutExam", coursesWithoutExam);
        model.addAttribute("teachersWithExam", teachersWithExam);
        model.addAttribute("studentsWithExam", studentsWithExam);
        return "exams/exam-view";
    }

    @GetMapping("/exam-addcourse/{id}")
    public String examAddCourse(@PathVariable("id") int id) {
        courseService.examAddCourse(currentExam, id);
        return "redirect:/exam-view/"+currentExam;
    }

    @GetMapping("/exam-deletecourse/{id}")
    public String examDeleteCourse(@PathVariable("id") int id) {
        courseService.examDeleteCourse(currentExam, id);
        return "redirect:/exam-view/"+currentExam;
    }

    @PostMapping("/updateExam")
    public String updateExam(@ModelAttribute Exam exam){
        examService.update(exam);
        return "redirect:/exam-view/"+exam.getExam_id();
    }

    @PostMapping("/deleteExam")
    public String deleteExam(@ModelAttribute Exam exam){
        examService.delete(exam.getExam_id());
        return "redirect:/exams";
    }

}
