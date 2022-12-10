package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Course;

import java.util.List;

public interface CourseService {

    Course loadCourse(Long courseId);

    Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId);

    Course createOrUpdate(Course course);

    List<Course> findCoursesByCourseName(String keyword);

    void assignStudentToCourse(Long courseId, Long studentId);

    List<Course> fetchAll();

    List<Course> fetchCoursesForStudent(Long studentId);

    void removeCourse(Long courseId);

}

