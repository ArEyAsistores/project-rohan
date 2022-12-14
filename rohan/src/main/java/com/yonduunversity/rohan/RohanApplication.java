package com.yonduunversity.rohan;

import com.itextpdf.text.DocumentException;
import com.yonduunversity.rohan.models.*;
import com.yonduunversity.rohan.models.dto.UserAccountDTO;
import com.yonduunversity.rohan.util.CertificateGen;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yonduunversity.rohan.services.UserService;

import java.io.FileNotFoundException;

@SpringBootApplication
public class RohanApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) throws DocumentException, FileNotFoundException {
		SpringApplication.run(RohanApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

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
					"admin.prohan@yahoo.com",
					"Project",
					"Rohan",
					 "ADMIN"));
		};
	}
}
