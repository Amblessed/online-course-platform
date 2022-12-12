package com.bright.onlinecoursesplatform.service;

import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11/12/2022
 */

@ExtendWith(MockitoExtension.class)
class InstructorServiceImplTest {

    @Mock
    private InstructorRepository instructorRepository;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private InstructorServiceImpl instructorService;

    @Test
    void testFindInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        Instructor actualInstructor = instructorService.findInstructorById(1L);

        assertEquals(instructor, actualInstructor);
    }

    @Test
    void testFindInstructorsByName() {
        String instructorName = "instructorFN";
        instructorService.findInstructorsByName(instructorName);
        verify(instructorRepository).findInstructorsByName(instructorName);
    }

    @Test
    void testFindInstructorByEmail() {
        String email = "instructor.email@gmail.com";
        instructorService.findInstructorByEmail(email);
        verify(instructorRepository).findInstructorByEmail(email);
    }

    @Test
    void testCreateInstructor() {
        User user = new User("user1@gmail.com", "test");
        when(userService.createUser(any(), any())).thenReturn(user);
        instructorService.createInstructor("fName", "lName", "summary", "user1@gmail.com", "test");
        verify(instructorRepository).save(any());
    }

    @Test
    void testUpdateInstructor() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);
        instructorService.updateInstructor(instructor);
        verify(instructorRepository).save(instructor);
    }

    @Test
    void testFetchInstructors() {
        instructorService.fetchInstructors();
        verify(instructorRepository).findAll();

    }

    @Test
    void testRemoveInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);
        Course course = new Course();
        course.setCourseId(1L);
        instructor.getCourses().add(course);

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        instructorService.removeInstructorById(1L);

        verify(courseService, times(1)).removeCourse(any());
        verify(instructorRepository).deleteById(any());
    }
}