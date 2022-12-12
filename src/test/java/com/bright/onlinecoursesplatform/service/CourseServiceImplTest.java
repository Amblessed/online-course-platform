package com.bright.onlinecoursesplatform.service;

import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.repository.CourseRepository;
import com.bright.onlinecoursesplatform.repository.InstructorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11/12/2022
 */

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void testLoadCourse() {
        Course course = new Course();
        course.setCourseId(1L);
        when(courseRepository.findById(any())).thenReturn(Optional.of(course));

        Course actualCourse = courseService.loadCourse(1L);
        assertEquals(course, actualCourse);
    }

    @Test
    void testLoadCourseException() {
        assertThrows(EntityNotFoundException.class,  () -> courseService.loadCourse(9L));
    }

    @Test
    void testCreateCourse() {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(1L);

        when(instructorRepository.findById(any())).thenReturn(Optional.of(instructor));

        courseService.createCourse("JPA","1h 30min","Hello JPA", 1L);

        verify(courseRepository).save(any());
    }

    @Test
    void testCreateOrUpdate() {
        Course course = new Course();
        course.setCourseId(1L);

        courseService.createOrUpdate(course);

        ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);

        verify(courseRepository).save(argumentCaptor.capture());

        Course capturedValue = argumentCaptor.getValue();

        assertEquals(course, capturedValue);
    }

    @Test
    void findCoursesByCourseName() {
    }

    @Test
    void assignStudentToCourse() {
    }

    @Test
    void fetchAll() {
    }

    @Test
    void fetchCoursesForStudent() {
    }

    @Test
    void removeCourse() {
    }
}