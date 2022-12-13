package com.bright.onlinecoursesplatform.controller;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12/12/2022
 */

import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.Student;
import com.bright.onlinecoursesplatform.service.CourseService;
import com.bright.onlinecoursesplatform.service.InstructorService;
import com.bright.onlinecoursesplatform.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.bright.onlinecoursesplatform.constants.ConstantValues.*;

@Controller
@RequestMapping(value = "/courses")
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;
    private final InstructorService instructorService;

    public CourseController(CourseService courseService, InstructorService instructorService, StudentService studentService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.studentService = studentService;
    }

    @GetMapping(value = INDEX)
    public String courses(Model model, @RequestParam(name = KEYWORD, defaultValue = "") String keyword){
        List<Course> courses = courseService.findCoursesByCourseName(keyword);
        model.addAttribute(COURSES, courses);
        model.addAttribute(KEYWORD, keyword);
        return "course-views/courses";
    }

    @GetMapping(value = DELETE)
    public String deleteCourse(Long courseId, String keyword){
        courseService.removeCourse(courseId);
        //return "redirect:/courses/index?keyword=" + keyword;
        return "redirect:/courses/index";
    }

    @GetMapping(value = "/editCourse")
    public String updateCourse(Model model, Long courseId){
        Course course = courseService.findCourseById(courseId);
        List<Instructor> instructors = instructorService.fetchInstructors();
        model.addAttribute(COURSE, course);
        model.addAttribute(INSTRUCTORS, instructors);
        model.addAttribute(PAGE_TITLE, "Update Course");
        return "course-views/edit-course";
    }

    @GetMapping(value = "/addCourse")
    public String createCourse(Model model){
        List<Instructor> instructors = instructorService.fetchInstructors();
        model.addAttribute(INSTRUCTORS, instructors);
        model.addAttribute(COURSE, new Course());
        model.addAttribute(PAGE_TITLE, "Add New Course");
        return "course-views/add-course";
    }

    @PostMapping(value = "/save")
    public String save(Course course){
        courseService.createOrUpdate(course);
        return "redirect:/courses/index";
    }

    @GetMapping("/index/student")
    public String coursesForCurrentStudent(Model model){
        Long studentId = 1L; //current student.would change later
        List<Course> registeredCourses = courseService.fetchCoursesForStudent(studentId);
        Student student = studentService.findStudentById(studentId);
        List<Course> otherCourses = courseService.fetchAll().stream().filter(course -> !registeredCourses.contains(course)).toList();
        model.addAttribute(COURSES, registeredCourses);
        model.addAttribute(PAGE_TITLE, student.getFirstName() + " " + student.getLastName());
        model.addAttribute(STUDENT, student);
        model.addAttribute("otherCourses", otherCourses);
        return "course-views/student-courses";
    }

    @GetMapping(value = "/enrollStudent")
    public String enrollCurrentStudentInCourse(Long courseId){
        Long studentId = 1L;
        courseService.assignStudentToCourse(courseId, studentId);
        return "redirect:/courses/index/student";

    }

    @GetMapping(value = "/unenroll")
    public String unenrollCurrentStudentInCourse(Long courseId){
        Long studentId = 1L;
        courseService.removeStudentFromCourse(courseId, studentId);
        return "redirect:/courses/index/student";
    }

    @GetMapping("/index/instructor")
    public String coursesForCurrentInstructor(Model model){
        Long instructorId = 1L;   //current instructor.
        Instructor instructor = instructorService.findInstructorById(instructorId);
        model.addAttribute(COURSES, instructor.getCourses());
        model.addAttribute(INSTRUCTOR, instructor);
        model.addAttribute(PAGE_TITLE, instructor.getFirstName() + " " + instructor.getLastName());
        return "course-views/instructor-courses";
    }

    @GetMapping("/instructor")
    public String coursesByInstructor(Model model, Long instructorId){
        //Long instructorId = 1L;   //current instructor.
        Instructor instructor = instructorService.findInstructorById(instructorId);
        model.addAttribute(COURSES, instructor.getCourses());
        model.addAttribute(INSTRUCTOR, instructor);
        model.addAttribute(PAGE_TITLE, instructor.getFirstName() + " " + instructor.getLastName());
        return "course-views/instructor-courses";
    }

}
