package com.gc.api.dondevoy.util.data;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gc.api.dondevoy.util.data.loader.DataLoaderRandom;

@Component
public class Data implements InitializingBean {
	
	@Value("${gc.data.random}")
	boolean gcDataRandom = false;

	@Autowired
	DataLoaderRandom dataLoaderRandom;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		// if needs we need to create an additional class to all data loader required
		if(gcDataRandom) {
			dataLoaderRandom.load();
		}
		
	}

}
