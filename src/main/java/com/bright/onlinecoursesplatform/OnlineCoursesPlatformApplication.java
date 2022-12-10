package com.bright.onlinecoursesplatform;

import com.bright.onlinecoursesplatform.repository.*;
import com.bright.onlinecoursesplatform.utility.OperationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineCoursesPlatformApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineCoursesPlatformApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//OperationUtility.usersOperations(userRepository);
		//OperationUtility.rolesOperations(roleRepository);
		//OperationUtility.assignRolesToUsers(userRepository, roleRepository);
		//OperationUtility.instructorsOperations(userRepository, instructorRepository, roleRepository);
		//OperationUtility.studentsOperations(userRepository, studentRepository, roleRepository);
		OperationUtility.coursesOperations(courseRepository, instructorRepository, studentRepository);

	}
}
