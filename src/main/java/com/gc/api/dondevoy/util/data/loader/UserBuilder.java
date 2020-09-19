package com.gc.api.dondevoy.util.data.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gc.api.dondevoy.model.User;
import com.gc.api.dondevoy.repository.UserRepository;
import com.github.javafaker.Faker;

@Component
public class UserBuilder {

	static Faker faker = new Faker();

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public List<User> buildRandomFull(int count) {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < count; i++) {
			User user = buildRandomUser();
			users.add(user);
		}

		// admin
		User user = User.builder()
				.firstName("Gonzalo Fabian")
				.lastName("Copa")
				.username("copagonzalo")
				.password(passwordEncoder.encode("test"))
				.email("copagonzalo@gmail.com")
			.build();
		users.add(user);
		return users;
	}

	public User buildRandomUser() {
		User user = User.builder()
				.firstName(faker.name().firstName())
				.lastName(faker.name().lastName())
				.username(faker.name().username())
				.password(passwordEncoder.encode(faker.internet().password()))
				.email(faker.internet().emailAddress())
			.build();
		return userRepository.save(user);
	}
}
