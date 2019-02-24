package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.Student;
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

    private List<Student> searchResult;
    private List<Student> studentList;

    @GetMapping("/students")
    public String students(Model model){
        studentList = studentService.findAll();
        model.addAttribute("studentList", studentList);
        return "students/students";
    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute Student student){
        studentService.create(student);
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
        model.addAttribute("student", student);
        return "students/student-view";
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
