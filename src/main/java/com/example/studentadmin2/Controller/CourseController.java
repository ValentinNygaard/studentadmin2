package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.*;
import com.example.studentadmin2.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;
    @Autowired
    TeacherServiceImpl teacherService;
    @Autowired
    ExerciseServiceImpl exerciseService;
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    ExamServiceImpl examService;

    private List<Course> searchResult;
    private List<Course> courseList;

    @GetMapping("/courses")
    public String courses(Model model){
        courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "courses/courses";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute Course course){
        courseService.create(course);
        return "redirect:/courses";
    }

    @PostMapping("/searchCourse")
    public String searchCourse(@ModelAttribute Course course){
        searchResult = courseService.searchByName(course.getCourse_title());
        if (searchResult.size()==0) {
            return "redirect:/course-nosearchresult";
        }
        return "redirect:/course-searchresult";
    }

    @GetMapping("/course-searchresult")
    public String courseSearchResult(Model model){
        model.addAttribute("searchResult", searchResult);
        return "courses/course-searchresult";
    }

    @GetMapping("/course-nosearchresult")
    public String courseNoResult(Model model){
        courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "courses/course-nosearchresult";
    }

    @GetMapping("/course-view/{id}")
    public String courseView(@PathVariable("id") int id, Model model) {
        Course course = courseService.findById(id);
        List<Teacher> teachersWithCourse = teacherService.teachersWithCourse(id);
        List<Exam> examsWithCourse = examService.examsWithCourse(id);
        List<Exercise> exercisesWithCourse = exerciseService.exercisesWithCourse(id);
        List<Student> studentsWithCourse = studentService.studentsWithCourse(id);
        model.addAttribute("course", course);
        model.addAttribute("teachersWithCourse", teachersWithCourse);
        model.addAttribute("examsWithCourse", examsWithCourse);
        model.addAttribute("exercisesWithCourse", exercisesWithCourse);
        model.addAttribute("studentsWithCourse", studentsWithCourse);
        return "courses/course-view";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(@ModelAttribute Course course){
        courseService.update(course);
        return "redirect:/course-view/"+course.getCourse_id();
    }

    @PostMapping("/deleteCourse")
    public String deleteCourse(@ModelAttribute Course course){
        courseService.delete(course.getCourse_id());
        return "redirect:/courses";
    }

}
