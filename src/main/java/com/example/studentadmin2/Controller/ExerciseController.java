package com.example.studentadmin2.Controller;

import com.example.studentadmin2.Model.Course;
import com.example.studentadmin2.Model.Exercise;
import com.example.studentadmin2.Service.CourseServiceImpl;
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
    ExerciseServiceImpl exerciseService;
    @Autowired
    CourseServiceImpl courseService;

    private List<Exercise> searchResult;
    private List<Exercise> exerciseList;
    private int currentExercise;

    @GetMapping("/exercises")
    public String exercise(Model model){
        exerciseList = exerciseService.findAll();
        model.addAttribute("exerciseList", exerciseList);
        return "exercises/exercises";
    }

    @PostMapping("/createExercise")
    public String createExercise(@ModelAttribute Exercise exercise){
        exerciseService.create(exercise);
        return "redirect:/exercises";
    }

    @PostMapping("/searchExercise")
    public String searchExercise(@ModelAttribute Exercise exercise){
        searchResult = exerciseService.searchByName(exercise.getExercise_title());
        if (searchResult.size()==0) {
            return "redirect:/exercise-nosearchresult";
        }
        return "redirect:/exercise-searchresult";
    }

    @GetMapping("/exercise-searchresult")
    public String exerciseSearchResult(Model model){
        model.addAttribute("searchResult", searchResult);
        return "exercises/exercise-searchresult";
    }

    @GetMapping("/exercise-nosearchresult")
    public String exerciseNoResult(Model model){
        exerciseList = exerciseService.findAll();
        model.addAttribute("exerciseList", exerciseList);
        return "exercises/exercise-nosearchresult";
    }

    @GetMapping("/exercise-view/{id}")
    public String exerciseView(@PathVariable("id") int id, Model model) {
        Exercise exercise = exerciseService.findById(id);
        currentExercise = exercise.getExercise_id();
        List<Course> coursesWithExercise = courseService.coursesWithExercise(id);
        List<Course> coursesWithoutExercise = courseService.coursesWithoutExercise(id);
        model.addAttribute("exercise", exercise);
        model.addAttribute("coursesWithExercise", coursesWithExercise);
        model.addAttribute("coursesWithoutExercise", coursesWithoutExercise);
        return "exercises/exercise-view";
    }

    @GetMapping("/exercise-addcourse/{id}")
    public String exerciseAddCourse(@PathVariable("id") int id) {
        courseService.exerciseAddCourse(currentExercise, id);
        return "redirect:/exercise-view/"+currentExercise;
    }

    @GetMapping("/exercise-deletecourse/{id}")
    public String exerciseDeleteCourse(@PathVariable("id") int id) {
        courseService.exerciseDeleteCourse(currentExercise, id);
        return "redirect:/exercise-view/"+currentExercise;
    }

    @PostMapping("/updateExercise")
    public String updateExercise(@ModelAttribute Exercise exercise){
        exerciseService.update(exercise);
        return "redirect:/exercise-view/"+exercise.getExercise_id();
    }

    @PostMapping("/deleteExercise")
    public String deleteExercise(@ModelAttribute Exercise exercise){
        exerciseService.delete(exercise.getExercise_id());
        return "redirect:/exercises";
    }

}
