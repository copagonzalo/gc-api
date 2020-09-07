package com.gc.api.dondevoy;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableConfigurationProperties(RepositoryRestProperties.class)
public class RepositoryRestConfig implements RepositoryRestConfigurer {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RepositoryRestProperties properties;

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

		// expose ID fields for entities
		config.exposeIdsFor(
				entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));

		// Globally configure CORS
		config.getCorsRegistry().addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("GET", "PUT",
				"PATCH", "POST", "DELETE", "OPTIONS");

		/*
		 * In order to handle @AuthenticationPrincipal, had to add config class
		 * RepositoryRestMvcConfig which extends RepositoryRestMvcConfiguration. By
		 * doing this, it messes up the config properties. This is a hack to fix it.
		 */
		this.properties.applyTo(config);
	}

	@Bean
	@Primary
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		Validator validator = validator();
		// bean validation always before save and create
		validatingListener.addValidator("beforeCreate", validator);
		validatingListener.addValidator("beforeSave", validator);
	}
}
