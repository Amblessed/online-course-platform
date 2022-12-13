package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.repository.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final CourseService courseService;

    private final UserService userService;

    public InstructorServiceImpl(InstructorRepository instructorRepository, CourseService courseService, UserService userService) {
        this.instructorRepository = instructorRepository;
        this.courseService = courseService;
        this.userService = userService;
    }

    @Override
    public Instructor findInstructorById(Long instructorId) {
        return instructorRepository.findById(instructorId).orElseThrow(() ->
                new EntityNotFoundException("Instructor with id " + instructorId + " not found"));
    }

    @Override
    public List<Instructor> findInstructorsByName(String name) {
        return instructorRepository.findInstructorsByName(name);
    }

    @Override
    public Instructor findInstructorByEmail(String email) {
        return instructorRepository.findInstructorByEmail(email);
    }

    @Override
    public Instructor createInstructor(String firstName, String lastName, String summary, String email, String password) {
        User user = userService.createUser(email, password);
        userService.assignRoleToUser(email, "Instructor");
        return instructorRepository.save(new Instructor(firstName, lastName, summary, user));
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {
        User user = userService.createUser(instructor.getUser().getEmail(), instructor.getUser().getPassword());
        userService.assignRoleToUser(instructor.getUser().getEmail(), "Instructor");
        return instructorRepository.save(new Instructor(instructor.getFirstName(), instructor.getLastName(), instructor.getSummary(), user));
    }

    @Override
    public Instructor updateInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public List<Instructor> fetchInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public void removeInstructorById(Long instructorId) {
        Instructor instructor = findInstructorById(instructorId);
        for(Course course: instructor.getCourses()){
            courseService.removeCourse(course.getCourseId());
        }
        instructorRepository.deleteById(instructorId);
    }
}
