package com.gc.api.dondevoy.util.data.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gc.api.dondevoy.model.User;
import com.gc.api.dondevoy.repository.UserRepository;
import com.github.javafaker.Faker;

@Component
public class UserBuilder {

	static Faker faker = new Faker();

	@Autowired
	UserRepository userRepository;

	public List<User> buildRandomFull(int count) {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < count; i++) {
			User user = buildRandomUser();
			users.add(user);
		}

		return users;
	}

	public User buildRandomUser() {
		User user = User.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName())
				.username(faker.name().username()).email(faker.internet().emailAddress()).build();
		return userRepository.save(user);
	}
}
