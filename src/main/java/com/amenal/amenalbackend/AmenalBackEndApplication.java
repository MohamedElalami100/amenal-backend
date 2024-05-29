package com.amenal.amenalbackend;

import com.amenal.amenalbackend.security.auth.AuthenticationService;
import com.amenal.amenalbackend.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.amenal.amenalbackend.security.user.Role.*;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AmenalBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmenalBackEndApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var supManager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admina@mail.com")
					.password("password")
					.role(SUPMANAGER)
					.build();
			System.out.println("Sup token: " + service.register(supManager).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

			var user = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("managera@mail.com")
					.password("password")
					.role(USER)
					.build();
			System.out.println("user token: " + service.register(user).getAccessToken());
		};
	}
}
