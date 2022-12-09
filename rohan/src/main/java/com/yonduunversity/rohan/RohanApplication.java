package com.yonduunversity.rohan;

import com.yonduunversity.rohan.models.ClassBatch;
import com.yonduunversity.rohan.models.Course;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yonduunversity.rohan.models.Role;
import com.yonduunversity.rohan.models.User;
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
					new ArrayList<>()), "student");
			userService.saveUser(new User(
					null,
					"sme.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					"rapog!h",
					true,
					new ArrayList<>()), "SME");
			userService.saveUser(new User(
					null,
					"student10.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					"rapog!h",
					true,
					new ArrayList<>()), "student");
			userService.saveUser(new User(
					null,
					"student2.rey@yahoo.com",
					"Roberto II",
					"Asistores",
					"rapog!h",
					true,
					new ArrayList<>()), "student");
			userService.addCourse(new Course("DATASTRUCT","Computer Programming","Data structures and algorithm",true));
//			userService.saveClass( new ClassBatch(5L,null, userService.addCourse(new Course(100L,"Computer Programming","Data structures and algorithm",true)),new ArrayList<>(), 10,10,30,50,null,null,true));
//			userService.enrollStudent("rna.rey@yahoo.com",5100L);
//			userService.enrollStudent("student2.rey@yahoo.com",5100L);
//			userService.enrollStudent("student10.rey@yahoo.com",5100L);


//			for (int x = 0; x < 20; x++) {// TEMP for PAGINATION
//				userService.saveUser(new User(
//						null,
//						"rna.rey" + x + "@yahoo.com",
//						"Roberto II",
//						"Asistores",
//						"rapog!h",
//						true,
//						new ArrayList<>()), "SME");
//			}

			////////////////////////
			/// INITIAL USER ROLE///
			//////////////////////

		};
	}
}
