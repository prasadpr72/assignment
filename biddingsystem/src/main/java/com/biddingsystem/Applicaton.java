package com.biddingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.biddingsystem.dao.UserRepository;
import com.biddingsystem.dto.User;

@SpringBootApplication
public class Applicaton {

	public static void main(String[] args) {
		SpringApplication.run(Applicaton.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository,
			BCryptPasswordEncoder crypt) {
		return args -> {

			userRepository.save(new User("user1", crypt.encode("password"),
					"user"));
			userRepository.save(new User("user2", crypt.encode("password"),
					"user"));
			userRepository.save(new User("user3", crypt.encode("password"),
					"user"));
			userRepository.save(new User("user4", crypt.encode("password"),
					"user"));
			userRepository.save(new User("user5", crypt.encode("password"),
					"user"));
			userRepository.save(new User("user6", crypt.encode("password"),
					"user"));
			userRepository.save(new User("user7", crypt.encode("password"),
					"admin"));
			userRepository.save(new User("user8", crypt.encode("password"),
					"user"));
			userRepository.save(new User("admin1", crypt.encode("password"),
					"admin"));
			userRepository.save(new User("admin2", crypt.encode("password"),
					"admin"));
		};
	}

}
