package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.Teacher;
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
public class TeacherController {


    @Autowired
    TeacherServiceImpl teacherService;

    private List<Teacher> searchResult;
    private List<Teacher> teacherList;

    @GetMapping("/teachers")
    public String teachers(Model model){
        teacherList = teacherService.findAll();
        model.addAttribute("teacherList", teacherList);
        return "teachers/teachers";
    }

    @PostMapping("/createTeacher")
    public String createTeacher(@ModelAttribute Teacher teacher){
        teacherService.create(teacher);
        return "redirect:/teachers";
    }

    @PostMapping("/searchTeacher")
    public String searchTeacher(@ModelAttribute Teacher teacher){
        searchResult = teacherService.searchByName(teacher.getTeacher_name());
        if (searchResult.size()==0) {
            return "redirect:/teacher-nosearchresult";
        }
        return "redirect:/teacher-searchresult";
    }

    @GetMapping("/teacher-searchresult")
    public String searchResult(Model model){
        model.addAttribute("searchResult", searchResult);
        return "teachers/teacher-searchresult";
    }

    @GetMapping("/teacher-nosearchresult")
    public String noResult(Model model){
        teacherList = teacherService.findAll();
        model.addAttribute("teacherList", teacherList);
        return "teachers/teacher-nosearchresult";
    }

    @GetMapping("/teacher-view/{id}")
    public String view(@PathVariable("id") int id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/teacher-view";
    }

    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute Teacher teacher){
        teacherService.update(teacher);
        return "redirect:/teacher-view/"+teacher.getTeacher_id();
    }

    @PostMapping("/deleteTeacher")
    public String deleteTeacher(@ModelAttribute Teacher teacher){
        teacherService.delete(teacher.getTeacher_id());
        return "redirect:/teachers";
    }

}
