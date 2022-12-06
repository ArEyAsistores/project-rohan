package com.yonduunversity.rohan;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.services.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class RohanApplication {

	public static void main(String[] args) {
		SpringApplication.run(RohanApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			///////////////////
			/// INITIAL ROLES///
			//////////////////
			userService.saveRole(new Role(null, "ADMIN"));
			userService.saveRole(new Role(null, "SME"));
			userService.saveRole(new Role(null, "STUDENT"));

			///////////////////
			/// INITIAL USERS///
			//////////////////
			userService.saveUser(new User(
					null,
					"aj.piedragoza@yahoo.com",
					"Almira Jane",
					"Piedragoza",
					"aj54@#1!",
					new ArrayList<>()));
			userService.saveUser(new User(
					null,
					"rna.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					"rapog!h",
					new ArrayList<>()));
			for (int x = 0; x < 20; x++) {// TEMP for PAGINATION
				userService.saveUser(new User(
						null,
						"rna.rey" + x + "@yahoo.com",
						"Roberto II",
						"Asistores",
						"rapog!h",
						new ArrayList<>()));
			}
			////////////////////////
			/// INITIAL USER ROLE///
			//////////////////////
			userService.addStudent(new User(
					null,
					"rey@yahoo.com",
					"Rober",
					"Asistores",
					"rapog!h",
					new ArrayList<>()), new Student());
			userService.assignRole("aj.piedragoza@yahoo.com", "ADMIN");
			userService.assignRole("rna.rey@yahoo.com", "STUDENT");

		};
	}
}
