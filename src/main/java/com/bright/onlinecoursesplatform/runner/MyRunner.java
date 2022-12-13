package com.bright.onlinecoursesplatform.runner;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.Student;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    StudentService studentService;
    @Autowired
    InstructorService instructorService;
    @Autowired
    CourseService courseService;

    private final String ADMIN = "Admin";
    private final String INSTRUCTOR = "Instructor";
    private final String STUDENT = "Student";

    //@Override
    public void run(String... args) {

        User user1 = userService.createUser("user1@gmail.com", "pass1");
        User user2 = userService.createUser("user2@gmail.com", "pass2");

        roleService.createRole(ADMIN);
        roleService.createRole(INSTRUCTOR);
        roleService.createRole(STUDENT);

        userService.assignRoleToUser(user1.getEmail(), ADMIN);
        userService.assignRoleToUser(user1.getEmail(), INSTRUCTOR);
        userService.assignRoleToUser(user2.getEmail(), STUDENT);

        Instructor instructor1 = instructorService.createInstructor("Terresa", "Stiedemann", "Experienced Instructor",
                "terresa.stiedemann.instructor@gmail.com", "pass1");
        Instructor instructor2 = instructorService.createInstructor("Nathan", "Ondricka", "Senior Instructor",
                "nathan.ondricka.instructor@gmail.com", "pass2");

        Student student1 = studentService.createStudent("Dennis", "Marks", "Beginner", "dennis.marks.student@gmail.com", "pass1");
        Student student2 = studentService.createStudent("Dora", "Medhurst", "Master Degree", "dora.medhurst.student@gmail.com", "pass2");

        Course course1 = courseService.createCourse("Introduction to Spring Data", "25 hours",
                "Master Spring Data JPA", instructor1.getInstructorId());
        Course course2 = courseService.createCourse("Introduction to Java Programming", "60 hours",
                "Master Programming using Java", instructor2.getInstructorId());

        courseService.assignStudentToCourse(course1.getCourseId(), student1.getStudentId());
        courseService.assignStudentToCourse(course2.getCourseId(), student2.getStudentId());

    }
}
