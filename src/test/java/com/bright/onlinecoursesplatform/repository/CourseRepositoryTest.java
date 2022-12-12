package com.bright.onlinecoursesplatform.repository;

import com.bright.onlinecoursesplatform.AbstractTest;
import com.bright.onlinecoursesplatform.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11/12/2022
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql", "file:src/test/resources/db/insert-values.sql"})
class CourseRepositoryTest extends AbstractTest{

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testFindCoursesByCourseNameContains() {
        List<Course> courses = courseRepository.findCoursesByCourseNameContains("Spring");
        int expectedResult = 2;
        assertEquals(expectedResult, courses.size());
    }

    @Test
    void testGetCoursesByStudentId() {

        List<Course> courses = courseRepository.getCoursesByStudentId(1L);
        int expectedResult = 1;
        assertEquals(expectedResult, courses.size());
    }
}