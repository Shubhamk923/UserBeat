package com.blogbeat.entry.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:8080");
		server.setDescription("Development");

		Contact myContact = new Contact();
		myContact.setName("Shubham Bhalchandra Khairanar");
		myContact.setEmail("khairnarshubham923@gmail.com");
Info information = new Info().title("BlogBeat Application Api").version("1.0")
				.description("This API exposes endpoints to manage users and there blogs info.").contact(myContact);
		return new OpenAPI().info(information).servers(List.of(server));

}
}
