package com.spot.on.config;

import com.spot.on.client.CoursesClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CoursesClientConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.spot.on.schema");
		return marshaller;
	}

	@Bean
	public CoursesClient coursesClient(Jaxb2Marshaller marshaller) {
		CoursesClient client = new CoursesClient();
		client.setDefaultUri("http://localhost:8080/ws");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

}
