package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Instructor;

import java.util.List;

public interface InstructorService {

    Instructor findInstructorById(Long instructorId);

    List<Instructor> findInstructorsByName(String name);

    Instructor findInstructorByEmail(String email);

    Instructor createInstructor(String firstName, String lastName, String summary, String email, String password);
    Instructor createInstructor(Instructor instructor);

    Instructor updateInstructor(Instructor instructor);

    List<Instructor> fetchInstructors();

    void removeInstructorById(Long instructorId);

}

