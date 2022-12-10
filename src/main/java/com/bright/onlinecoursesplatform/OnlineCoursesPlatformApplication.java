package com.bright.onlinecoursesplatform;

import com.bright.onlinecoursesplatform.entity.Course;
import com.bright.onlinecoursesplatform.entity.Instructor;
import com.bright.onlinecoursesplatform.entity.Student;
import com.bright.onlinecoursesplatform.entity.User;
import com.bright.onlinecoursesplatform.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineCoursesPlatformApplication {

	private final String ADMIN = "Admin";
	private final String INSTRUCTOR = "Instructor";
	private final String STUDENT = "Student";


	public static void main(String[] args) {
		SpringApplication.run(OnlineCoursesPlatformApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService, RoleService roleService, StudentService studentService, InstructorService
							instructorService, CourseService courseService){
		return args -> {
			User user1 = userService.createUser("user1@gmail.com", "pass1");
			User user2 = userService.createUser("user2@gmail.com", "pass2");

			roleService.createRole(ADMIN);
			roleService.createRole(INSTRUCTOR);
			roleService.createRole(STUDENT);

			userService.assignRoleToUser(user1.getEmail(), ADMIN);
			userService.assignRoleToUser(user1.getEmail(), INSTRUCTOR);
			userService.assignRoleToUser(user2.getEmail(), STUDENT);

			Instructor instructor1 = instructorService.createInstructor("instructor1FN", "instructor1LN", "Experienced Instructor",
					"instructorUser1@gmail.com", "pass1");
			Instructor instructor2 = instructorService.createInstructor("instructor2FN", "instructor2LN", "Senior Instructor",
					"instructorUser2@gmail.com", "pass2");

			Student student1 = studentService.createStudent("student1FN", "student1LN", "Beginner", "student1@gmail.com", "pass1");
			Student student2 = studentService.createStudent("student2FN", "student2LN", "Master Degree", "student2@gmail.com", "pass2");

			Course course1 = courseService.createCourse("Introduction to Spring Data", "25 hours",
					"Master Spring Data JPA", instructor1.getInstructorId());
			Course course2 = courseService.createCourse("Introduction to Java Programming", "60 hours",
					"Master Programming using Java", instructor2.getInstructorId());

			courseService.assignStudentToCourse(course1.getCourseId(), student1.getStudentId());
			courseService.assignStudentToCourse(course2.getCourseId(), student2.getStudentId());
		};
	}
}
