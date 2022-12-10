package com.bright.onlinecoursesplatform.service;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.Student;
import com.bright.onlinecoursesplatform.repository.CourseRepository;
import com.bright.onlinecoursesplatform.repository.InstructorRepository;
import com.bright.onlinecoursesplatform.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  //This avoids the lazy initialization problem.
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Course loadCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() ->
                new EntityNotFoundException("Course with id " + courseId + " not found"));
    }

    @Override
    public Course createCourse(String courseName, String courseDuration, String courseDescription, Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() ->
                new EntityNotFoundException("Instructor with id " + instructorId + " not found"));
        return courseRepository.save(new Course(courseName, courseDuration, courseDescription, instructor));
    }

    @Override
    public Course createOrUpdate(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> findCoursesByCourseName(String keyword) {
        return courseRepository.findCoursesByCourseNameContains(keyword);
    }

    @Override
    public void assignStudentToCourse(Long courseId, Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new EntityNotFoundException("Student with id " + studentId + " not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() ->
                new EntityNotFoundException("Student with id " + courseId + " not found"));
        course.assignStudentToCourse(student);
    }

    @Override
    public List<Course> fetchAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> fetchCoursesForStudent(Long studentId) {
        return courseRepository.getCoursesByStudentId(studentId);
    }

    @Override
    public void removeCourse(Long courseId) {
        courseRepository.deleteById(courseId);

    }
}
