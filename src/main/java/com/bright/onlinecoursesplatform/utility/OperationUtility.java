package com.bright.onlinecoursesplatform.utility;
/*
 * @Project Name: Online Courses Platform
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10/12/2022
 */


import com.bright.onlinecoursesplatform.entity.*;
import com.bright.onlinecoursesplatform.repository.*;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OperationUtility {

    static Faker faker = new Faker();

    public static void usersOperations(UserRepository userRepository){
        createUsers(userRepository);
        //updateUser(userRepository);
        //deleteUser(userRepository);
        //fetchUsers(userRepository);
    }
    public static void rolesOperations(RoleRepository roleRepository){
        createRoles(roleRepository);
        //updateRole(roleRepository);
        //deleteRole(roleRepository);
        //fetchRoles(roleRepository);
    }

    public static void instructorsOperations(UserRepository userRepository, InstructorRepository instructorRepository, RoleRepository roleRepository){
        createInstructors(userRepository, instructorRepository, roleRepository);
        //updateInstructor(instructorRepository);
        //deleteInstructor(instructorRepository);
        //fetchInstructors(instructorRepository);
    }

    public static void studentsOperations(UserRepository userRepository, StudentRepository studentRepository, RoleRepository roleRepository){
        createStudents(userRepository, studentRepository, roleRepository);
        //updateStudent(studentRepository);
        //deleteStudent(studentRepository);
        //fetchStudents(studentRepository);
    }

    public static void coursesOperations(CourseRepository courseRepository, InstructorRepository instructorRepository, StudentRepository studentRepository){
        //createCourses(courseRepository, instructorRepository);
        //updateCourse(courseRepository);
        //deleteCourse(courseRepository);
       //fetchCourses(courseRepository);
        //assignStudentsToCourses(courseRepository, studentRepository);
        fetchCoursesForStudent(courseRepository);
    }

    private static void createUsers(UserRepository userRepository) {
        User user1 = new User("user1@gmail.com", "password1");
        userRepository.save(user1);

        User user2 = new User("user2@gmail.com", "password2");
        userRepository.save(user2);

        User user3 = new User("user3@gmail.com", "password3");
        userRepository.save(user3);

        User user4 = new User("user4@gmail.com", "password4");
        userRepository.save(user4);

        User user = new User(faker.internet().emailAddress(), RandomStringUtils.randomAscii(12).replace(" ", ""));
        userRepository.save(user);

        user = new User(faker.internet().emailAddress(), RandomStringUtils.randomAscii(12).replace(" ", ""));
        userRepository.save(user);

        user = new User(faker.internet().emailAddress(), RandomStringUtils.randomAscii(12).replace(" ", ""));
        userRepository.save(user);

        user = new User(faker.internet().emailAddress(), RandomStringUtils.randomAscii(12).replace(" ", ""));
        userRepository.save(user);
    }

    private static void updateUser(UserRepository userRepository) {
        User user = userRepository.findById(2L).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEmail("user2NewEmail@gmail.com");
        userRepository.save(user);
    }

    private static void deleteUser(UserRepository userRepository) {
        User user = userRepository.findById(3L).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    private static void fetchUsers(UserRepository userRepository) {
        userRepository.findAll().forEach(System.out::println);
    }

    private static void createRoles(RoleRepository roleRepository) {
        Role role1 = new Role("Admin");
        roleRepository.save(role1);

        Role role2 = new Role("Instructor");
        roleRepository.save(role2);

        Role role3 = new Role("Student");
        roleRepository.save(role3);
    }

    private static void updateRole(RoleRepository roleRepository) {
        Role role = roleRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        role.setName("NewAdmin");
        roleRepository.save(role);
    }

    private static void deleteRole(RoleRepository roleRepository) {
        roleRepository.deleteById(2L);
    }

    private static void fetchRoles(RoleRepository roleRepository) {
        roleRepository.findAll().forEach(System.out::println);
    }

    public static void assignRolesToUsers(UserRepository userRepository, RoleRepository roleRepository){
        Role role = roleRepository.findByName("Admin");
        if(Objects.isNull(role)){
            throw new EntityNotFoundException("Role not found");
        }
        List<User> users = userRepository.findAll();
        users.forEach(
                user -> {
                    user.assignRoleToUser(role);
                    userRepository.save(user);
                }
        );
    }

    private static void createInstructors(UserRepository userRepository, InstructorRepository instructorRepository, RoleRepository roleRepository) {
        Role role = roleRepository.findByName("Instructor");
        if(Objects.isNull(role)){
            throw new EntityNotFoundException("Role not found");
        }

        User user = new User("instructor1@gmail.com", "passIns1");
        user.assignRoleToUser(role);
        userRepository.save(user);

        Instructor instructor = new Instructor("instructor1FN", "instructor1LN", "Experienced Instructor", user);
        instructorRepository.save(instructor);


        user = new User("instructor2@gmail.com", "passIns2");
        user.assignRoleToUser(role);
        userRepository.save(user);
        instructor = new Instructor("instructor2FN", "instructor2LN", "Senior Instructor", user);
        instructorRepository.save(instructor);
    }

    private static void updateInstructor(InstructorRepository instructorRepository) {
        Instructor instructor = instructorRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
        instructor.setSummary("Certified Instructor");
        instructorRepository.save(instructor);
    }

    private static void deleteInstructor(InstructorRepository instructorRepository) {
        instructorRepository.deleteById(2L);
    }

    private static void fetchInstructors(InstructorRepository instructorRepository) {
        instructorRepository.findAll().forEach(System.out::println);
    }


    private static void createStudents(UserRepository userRepository, StudentRepository studentRepository, RoleRepository roleRepository) {
        Role role = roleRepository.findByName("Student");
        if(Objects.isNull(role)){
            throw new EntityNotFoundException("Role not found");
        }

        User user = new User("student1@gmail.com", "passStud1");
        user.assignRoleToUser(role);
        userRepository.save(user);

        Student student = new Student("student1FN", "student1LN", "Master Degree", user);
        studentRepository.save(student);

        user = new User("student2@gmail.com", "passStud2");
        user.assignRoleToUser(role);
        userRepository.save(user);

        student = new Student("student2FN", "student2LN", "Phd", user);
        studentRepository.save(student);
    }

    private static void updateStudent(StudentRepository studentRepository) {

        Student student = studentRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        student.setFirstName("updatedStudent1FN");
        student.setLastName("updatedStudent1LN");
        studentRepository.save(student);
    }

    private static void deleteStudent(StudentRepository studentRepository) {
        studentRepository.deleteById(1L);
    }

    private static void fetchStudents(StudentRepository studentRepository) {
        studentRepository.findAll().forEach(System.out::println);
    }

    private static void createCourses(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        Instructor instructor = instructorRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor not found"));

        Course course = new Course("Intro to Spring Boot", "5 Hours",
                "An Introduction to Spring Boot with Java, Intellij, and Maven", instructor);
        courseRepository.save(course);

       course = new Course("Intro to Java Programming", "10 Hours",
                "An Introduction to Programming with Java 17 using Intellij IDE", instructor);
        courseRepository.save(course);

    }

    private static void updateCourse(CourseRepository courseRepository) {
        Course course = courseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
        course.setCourseDuration("20 Hours");
        courseRepository.save(course);
    }

    private static void deleteCourse(CourseRepository courseRepository) {
        courseRepository.deleteById(2L);
    }

    private static void fetchCourses(CourseRepository courseRepository) {
        courseRepository.findAll().forEach(System.out::println);
    }

    private static void assignStudentsToCourses(CourseRepository courseRepository, StudentRepository studentRepository){
        Optional<Student> student1 = studentRepository.findById(1L);
        Optional<Student> student2 = studentRepository.findById(2L);
        Course course = courseRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Course not found"));

        student1.ifPresent(course::assignStudentToCourse);
        student2.ifPresent(course::assignStudentToCourse);
        courseRepository.save(course);
    }

    private static void fetchCoursesForStudent(CourseRepository courseRepository) {
        courseRepository.getCoursesByStudentId(1L).forEach(System.out::println);
    }


}
