package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Student;

import java.util.List;

public interface StudentService {

    Student findStudentById(Long studentId);

    List<Student> findStudentsByName(String name);

    Student findStudentByEmail(String email);

    Student createStudent(String firstName, String lastName, String level, String email, String password);

    Student update(Student student);

    List<Student> fetchStudents();

    void removeStudent(Long studentId);

}

