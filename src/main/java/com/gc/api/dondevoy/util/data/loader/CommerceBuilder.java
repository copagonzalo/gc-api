package com.gc.api.dondevoy.util.data.loader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gc.api.dondevoy.model.Commerce;
import com.gc.api.dondevoy.repository.CommerceRepository;
import com.github.javafaker.Faker;

@Component
public class CommerceBuilder {
	static Faker faker = new Faker();

	@Autowired
	CommerceRepository commercialRepository;

	public List<Commerce> buildRandomFull(int count) {
		List<Commerce> commerces = new ArrayList<Commerce>();
		for (int i = 0; i < count; i++) {
			Commerce commerce = buildRandomComercial();
			commerces.add(commerce);
		}

		return commerces;
	}

	public Commerce buildRandomComercial() {
		Commerce commercial = Commerce.builder().name(faker.company().name()).build();
		return commercialRepository.save(commercial);
	}
}
