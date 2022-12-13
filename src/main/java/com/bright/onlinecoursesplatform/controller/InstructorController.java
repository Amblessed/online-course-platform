package com.bright.onlinecoursesplatform.controller;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 13/12/2022
 */

import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.service.InstructorService;
import com.bright.onlinecoursesplatform.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

import static com.bright.onlinecoursesplatform.constants.ConstantValues.*;

@Controller
@RequestMapping(value = "/instructors")
public class InstructorController {

    private final InstructorService instructorService;
    private final UserService userService;

    public InstructorController(InstructorService instructorService, UserService userService) {
        this.instructorService = instructorService;
        this.userService = userService;
    }

    @GetMapping(value = INDEX)
    public String instructors(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword){
        List<Instructor> instructors = instructorService.findInstructorsByName(keyword);
        model.addAttribute(INSTRUCTORS, instructors);
        model.addAttribute(KEYWORD, keyword);
        model.addAttribute(PAGE_TITLE, "Instructors");
        return "instructor-views/instructors";
    }

    @GetMapping(value = "/delete")
    public String deleteCourse(Long instructorId, String keyword){
        instructorService.removeInstructorById(instructorId);
        return "redirect:/instructors/index?keyword=" + keyword;
    }

    @GetMapping(value = "/editInstructor")
    public String updateInstructor(Model model, Long instructorId){
        //current instructor
        Instructor instructor = instructorService.findInstructorById(instructorId);
        model.addAttribute(INSTRUCTOR, instructor);
        model.addAttribute(PAGE_TITLE, "Update Instructor");
        return "instructor-views/edit-instructor";
    }

    @PostMapping(value = "/update")
    public String update(Instructor instructor){
        instructorService.updateInstructor(instructor);
        return "redirect:/instructors/index";
    }

    @GetMapping(value = "/addInstructor")
    public String createCourse(Model model){
        model.addAttribute(INSTRUCTOR, new Instructor());
        model.addAttribute(PAGE_TITLE, "Add New Instructor");
        return "instructor-views/add-instructor";
    }

    @PostMapping(value = "/save")
    public String save(@Valid Instructor instructor, BindingResult bindingResult){
        User user = userService.findUserByEmail(instructor.getUser().getEmail());
        if(Objects.nonNull(user)){
            bindingResult.rejectValue("user.email", "409", "There is already an account registered with this email");
        }
        if(bindingResult.hasErrors()){
            return "instructor-views/add-instructor";
        }
       /* instructorService.createInstructor(instructor.getFirstName(),instructor.getLastName(), instructor.getSummary(), instructor.getUser().getEmail(),
                instructor.getUser().getPassword());*/
        instructorService.createInstructor(instructor);
        return "redirect:/instructors/index";
    }

}
