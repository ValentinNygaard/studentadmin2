package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.Exam;
import com.example.studentadmin2.Service.ExamServiceImpl;
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

    private List<Exam> searchResult;
    private List<Exam> examList;

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
    public String ExamSearchResult(Model model){
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
        model.addAttribute("exam", exam);
        return "exams/exam-view";
    }

    @PostMapping("/updateExam")
    public String updateExam(@ModelAttribute Exam exam){
        examService.update(exam);
        return "redirect:/student-view/"+exam.getExam_id();
    }

    @PostMapping("/deleteExam")
    public String deleteExam(@ModelAttribute Exam exam){
        examService.delete(exam.getExam_id());
        return "redirect:/exams";
    }

}
