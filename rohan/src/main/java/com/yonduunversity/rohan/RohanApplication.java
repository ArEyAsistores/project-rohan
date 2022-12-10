package com.yonduunversity.rohan;

import com.yonduunversity.rohan.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.services.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class RohanApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(RohanApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	ClassBatchRepo classBatchRepo;
	@Autowired
	StudentRepo studentRepo;

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {

			userService.saveRole(new Role(null, "ADMIN"));
			userService.saveRole(new Role(null, "SME"));
			userService.saveRole(new Role(null, "STUDENT"));

			 ///////////////////
			/// INITIAL USERS///
			//////////////////
			userService.defaultUsers(new UserAccountDTO(
					"aj.piedragoza@yahoo.com",
					"Almira Jane",
					"Piedragoza",
					 "ADMIN"));
			userService.defaultUsers(new UserAccountDTO(
					"rna.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					"student"));
			userService.defaultUsers(new UserAccountDTO(
					"sme.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					 "SME"));
			userService.defaultUsers(new UserAccountDTO(
					"student10.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					 "student"));
			userService.defaultUsers(new UserAccountDTO(
					"student2.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					"student"));
			userService
					.addCourse(new Course("DATASTRUCT", "Computer Programming", "Data structures and algorithm", true, new ArrayList<>()));

		};
	}
}
