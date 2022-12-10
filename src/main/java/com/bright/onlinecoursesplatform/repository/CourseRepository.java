package com.bright.onlinecoursesplatform.repository;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCoursesByCourseNameContains(String keyword);

    @Query(value = "select * from tbl_courses as c where c.course_id in (select e.course_id from enrolled_in as e where e.student_id=:studentId)",
            nativeQuery = true)
    List<Course> getCoursesByStudentId(@Param("studentId") Long studentId);

}

