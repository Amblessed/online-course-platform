package com.bright.onlinecoursesplatform.controller;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 13/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.Student;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.service.StudentService;
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
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping(value = INDEX)
    public String students(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword){
        List<Student> students = studentService.findStudentsByName(keyword);
        model.addAttribute(STUDENTS, students);
        model.addAttribute(KEYWORD, keyword);
        model.addAttribute(PAGE_TITLE, "Students View");
        return "student-views/students";
    }

    @GetMapping(value = DELETE)
    public String deleteCourse(Long studentId, String keyword){
        studentService.removeStudent(studentId);
        //return "redirect:/courses/index?keyword=" + keyword;
        return "redirect:/students/index";
    }

    @GetMapping(value = "/editStudent")
    public String updateStudent(Model model, Long studentId){
        //current student
        Student student = studentService.findStudentById(studentId);
        model.addAttribute(STUDENT, student);
        model.addAttribute(PAGE_TITLE, student.getFirstName() + " " + student.getLastName());
        return "student-views/edit-student";
    }

    @PostMapping(value = "/update")
    public String save(Student student){
        studentService.update(student);
        return "redirect:/students/index";
    }

    @GetMapping(value = "/addStudent")
    public String createCourse(Model model){
        model.addAttribute(STUDENT, new Student());
        model.addAttribute(PAGE_TITLE, "Add New Student");
        return "student-views/add-student";
    }

    @PostMapping(value = "/save")
    public String save(@Valid Student student, BindingResult bindingResult){
        User user = userService.findUserByEmail(student.getUser().getEmail());
        if(Objects.nonNull(user)){
            bindingResult.rejectValue("user.email", "", "There is already an account registered with this email");
        }
        if(bindingResult.hasErrors()){
            return "student-views/add-student";
        }
        /*studentService.createStudent(student.getFirstName(),student.getLastName(), student.getLevel(), student.getUser().getEmail(),
                student.getUser().getPassword());*/
        studentService.createStudent(student);
        return "redirect:/students/index";
    }

}
