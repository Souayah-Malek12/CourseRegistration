package Controller;


import Entity.Course;
import Service.CourseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;


    @RequestMapping(value="saveCourse", method= RequestMethod.POST)
    public String saveCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, @RequestParam("courseid") Long courseId, Model model){
            if(!bindingResult.hasErrors()){
                courseService.saveCourse(course);
            }else{
                String title = (courseId==null)? "Add Course" : "Edit Course";
                model.addAttribute("title", title);
                return "courseForm";
            }
            return "redirect:/courses";
    }

    @RequestMapping("courses")
    public String index(Model model){
        List<Course> courses = (List<Course>) courseService.findAllByOrderByNameAsc();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @RequestMapping("course/new")
    public String newCourse(Model model){
        model.addAttribute("course", new Course());
        model.addAttribute("title", "Add Course");
        return "courseForm";
    }

    @RequestMapping("course/edit/{id}")
    public String editCourse(@PathVariable("id") Long courseId, Model model){
        model.addAttribute("course", courseService.findCourseById(courseId));
        model.addAttribute("title", "Edit Course");
        return "courseForm";
    }

    @RequestMapping("course/{id}")
    public String showCourse(@PathVariable("id") Long courseId, Model model){
        model.addAttribute("course", courseService.findCourseById(courseId));
        model.addAttribute("title", "Edit Course");
        return "courseShow";
    }

    @RequestMapping(value = "course/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") Long courseId, Model model) {
        courseService.deleteCourseById(courseId);
        return "redirect:/courses";
    }

}
