package com.bright.onlinecoursesplatform.repository;

import com.bright.onlinecoursesplatform.entity.Instructor;
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
class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void testFindInstructorsByName() {
        List<Instructor> instructors = instructorRepository.findInstructorsByName("instructor1FN");
        int expectedResult = 1;
        assertEquals(expectedResult, instructors.size());
    }

    @Test
    void findInstructorByEmail() {
        Instructor instructor = instructorRepository.findInstructorByEmail("instructorUser1@gmail.com");
        assertEquals("instructor1FN", instructor.getFirstName());
        assertEquals("instructor1LN", instructor.getLastName());
        assertEquals("Experienced Instructor", instructor.getSummary());
    }

    @Test
    void testFindInstructorByNotExistingEmail(){
        Instructor instructor = instructorRepository.findInstructorByEmail("not.existing@gmail.com");
        assertNull(instructor);
    }
}