package com.pchudzik.blog.repositorytesting;

import com.pchudzik.blog.repositorytesting.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@RequiredArgsConstructor
public class RepositoryTestingApplication {
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(RepositoryTestingApplication.class, args);
	}

	@PostConstruct
	public void printUsers() {
		System.out.println("Users: " + userRepository.findAll());
	}
}
