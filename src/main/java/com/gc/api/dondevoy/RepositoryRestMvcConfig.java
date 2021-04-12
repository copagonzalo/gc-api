package com.gc.api.dondevoy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

@Configuration
public class RepositoryRestMvcConfig  extends RepositoryRestMvcConfiguration {

	public RepositoryRestMvcConfig(ApplicationContext context, ObjectFactory<ConversionService> conversionService) {
		super(context, conversionService);
	}

	@Override
	protected List<HandlerMethodArgumentResolver> defaultMethodArgumentResolvers() {
		List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
		resolvers.addAll(super.defaultMethodArgumentResolvers());
		return resolvers;
	}
}
