package com.gc.api.dondevoy.util.data.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class DataLoaderRandom {

	@Autowired
	CommerceBuilder commerceBuilder;
	
	@Autowired
	UserBuilder userBuilder;
	
	public void load() {
		commerceBuilder.buildRandomFull(10);
		userBuilder.buildRandomFull(10);
	}
}
