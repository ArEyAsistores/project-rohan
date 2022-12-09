package com.yonduunversity.rohan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
import com.yonduunversity.rohan.models.student.Student;
import com.yonduunversity.rohan.repository.ClassBatchRepo;
import com.yonduunversity.rohan.repository.StudentRepo;
import com.yonduunversity.rohan.services.UserService;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RohanApplication {

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

					true,
					new ArrayList<>()), "ADMIN");

			userService.saveUser(new User(
					null,
					"rna.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					"rapog!h",
					true,
					new ArrayList<>()), "STUDENT");

			for (int x = 0; x < 20; x++) {// TEMP for PAGINATION
				userService.saveUser(new User(
						null,
						"rna.rey" + x + "@yahoo.com",
						"Roberto II",
						"Asistores",
						"rapog!h",
						true,
						new ArrayList<>()), "SME");
			}

			////////////////////////
			/// INITIAL USER ROLE///
			//////////////////////
			classBatchRepo.save(new ClassBatch());
			Student student1 = new Student();
			student1.setEmail("hello@world.com");
			studentRepo.save(student1);
			Student student2 = new Student();
			student2.setEmail("hello2@world.com");
			studentRepo.save(student2);

		};
	}
}
