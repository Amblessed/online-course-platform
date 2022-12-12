package com.bright.onlinecoursesplatform.service;

import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Student;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
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
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private UserService userService;

    @Mock
    private StudentRepository studentRepository;


    @Test
    void testFindStudentById() {
        Student student = new Student();
        student.setStudentId(1L);

        when(studentRepository.findById(any())).thenReturn(Optional.of(student));

        Student actualStudent = studentService.findStudentById(1L);
        assertEquals(student, actualStudent);
    }

    @Test
    void testFindStudentException() {
        assertThrows(EntityNotFoundException.class,  () -> studentService.findStudentById(287L));
    }

    @Test
    void testFindStudentsByName() {
        String name = "name";
        studentService.findStudentsByName(name);
        verify(studentRepository).findStudentsByName(name);
    }

    @Test
    void testFindStudentByEmail() {
        String email = "name@gmail.com";
        studentService.findStudentByEmail(email);
        verify(studentRepository).findStudentByEmail(email);
    }

    @Test
    void testCreateStudent() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("adpass");

        when(userService.createUser(any(),any())).thenReturn(user);

        studentService.createStudent("FN","LN","master","user@gmail.com","adpass");

        verify(studentRepository).save(any());
    }

    @Test
    void testUpdate() {
        Student student = new Student();
        student.setStudentId(1L);
        studentService.update(student);
        verify(studentRepository).save(student);
    }

    @Test
    void testFetchStudents() {
        studentService.fetchStudents();
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testRemoveStudent() {
        Student student = new Student();
        student.setStudentId(1L);

        Course course = new Course();
        course.setCourseId(1L);
        student.getCourses().add(course);

        when(studentRepository.findById(any())).thenReturn(Optional.of(student));

        studentService.removeStudent(1L);

        verify(studentRepository).deleteById(any());
    }
}