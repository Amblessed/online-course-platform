package com.bright.onlinecoursesplatform.repository;

import com.bright.onlinecoursesplatform.entity.Student;
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
 * @Created: 12/12/2022
 */


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"file:src/test/resources/db/clearAll.sql", "file:src/test/resources/db/insert-values.sql"})
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findStudentsByName() {
        List<Student> students = studentRepository.findStudentsByName("std");
        assertEquals(2, students.size());
    }

    @Test
    void findStudentByEmail() {
        Student student = studentRepository.findStudentByEmail("stdUser2@gmail.com");
        assertNotNull(student);
        assertEquals("master degree", student.getLevel());
    }
}