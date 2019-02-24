package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.Exercise;
import com.example.studentadmin2.Service.ExerciseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class ExerciseController {


    @Autowired
    ExerciseServiceImpl exerciceService;

    private List<Exercise> searchResult;
    private List<Exercise> exerciceList;

    @GetMapping("/exercices")
    public String exercice(Model model){
        exerciceList = exerciceService.findAll();
        model.addAttribute("exerciceList", exerciceList);
        return "exercices/exercices";
    }

    @PostMapping("/createExercise")
    public String createExercise(@ModelAttribute Exercise exercice){
        exerciceService.create(exercice);
        return "redirect:/exercices";
    }

    @PostMapping("/searchExercise")
    public String searchExercise(@ModelAttribute Exercise exercice){
        searchResult = exerciceService.searchByName(exercice.getExercise_title());
        if (searchResult.size()==0) {
            return "redirect:/exercice-nosearchresult";
        }
        return "redirect:/exercice-searchresult";
    }

    @GetMapping("/exercice-searchresult")
    public String exerciseSearchResult(Model model){
        model.addAttribute("searchResult", searchResult);
        return "exercices/exercice-searchresult";
    }

    @GetMapping("/exercice-nosearchresult")
    public String exerciceNoResult(Model model){
        exerciceList = exerciceService.findAll();
        model.addAttribute("exerciceList", exerciceList);
        return "exercices/exercice-nosearchresult";
    }

    @GetMapping("/exercice-view/{id}")
    public String exerciceView(@PathVariable("id") int id, Model model) {
        Exercise exercice = exerciceService.findById(id);
        model.addAttribute("exercice", exercice);
        return "exercices/exercice-view";
    }

    @PostMapping("/updateExercise")
    public String updateExercise(@ModelAttribute Exercise exercice){
        exerciceService.update(exercice);
        return "redirect:/exercice-view/"+exercice.getExercise_id();
    }

    @PostMapping("/deleteExercise")
    public String deleteExercise(@ModelAttribute Exercise exercice){
        exerciceService.delete(exercice.getExercise_id());
        return "redirect:/exercices";
    }

}
