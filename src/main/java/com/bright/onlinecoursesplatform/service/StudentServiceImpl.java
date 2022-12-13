package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Student;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final UserService userService;

    public StudentServiceImpl(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() ->
                new EntityNotFoundException("Student with id " + studentId + " not found"));
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        return studentRepository.findStudentsByName(name);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    @Override
    public Student createStudent(String firstName, String lastName, String level, String email, String password) {
        User user = userService.createUser(email, password);
        userService.assignRoleToUser(email, "Student");
        return studentRepository.save(new Student(firstName, lastName, level, user));
    }

    @Override
    public Student createStudent(Student student) {
        User user = userService.createUser(student.getUser().getEmail(), student.getUser().getPassword());
        userService.assignRoleToUser(student.getUser().getEmail(), "Student");
        return studentRepository.save(new Student(student.getFirstName(), student.getLastName(), student.getLevel(), user));
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> fetchStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void removeStudent(Long studentId) {
        Student student = findStudentById(studentId);
        Set<Course> courses = student.getCourses();
        courses.forEach(course -> course.removeStudentFromCourse(student));
        studentRepository.deleteById(studentId);
    }
}
