package be.lokapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition
@ComponentScan(basePackages = {"be.lokapi"})
@EnableJpaRepositories(basePackages = "be.lokapi.repository")
public class LokapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LokapiApplication.class, args);
	}

}
