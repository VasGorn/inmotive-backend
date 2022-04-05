package com.vesmer.inmotive;

import com.vesmer.inmotive.config.SwaggerConfig;
import com.vesmer.inmotive.dto.RegisterRequest;
import com.vesmer.inmotive.model.Project;
import com.vesmer.inmotive.model.User;
import com.vesmer.inmotive.repository.ProjectRepository;
import com.vesmer.inmotive.repository.UserRepository;
import com.vesmer.inmotive.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.Instant;
import java.util.Arrays;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class InmotiveBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(InmotiveBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AuthService authService, ProjectRepository projectRepository,
						  UserRepository userRepository) {
		return args -> {
			initialUserRegistration(authService, userRepository);

			User user = userRepository.findByUsername("nix").orElseThrow(
					() -> new UsernameNotFoundException("Username not found - nix")
			);
			createUserProjects(user, projectRepository);
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	private void initialUserRegistration(AuthService authService, UserRepository userRepository){
		RegisterRequest registerRequest = new RegisterRequest("nix-email@mail.com",
				"nix", "1111");
		authService.signup(registerRequest);
		authService.enableAccount("nix");
	}

	private void createUserProjects(User user, ProjectRepository projectRepository) {
			Project project1 = Project.builder()
					.name("first name project")
					.description("first description project")
					.supplyVoltage(380.0)
					.supplyFrequency(50)
					.maxSlip(0.2)
					.user(user)
					.created(Instant.ofEpochSecond(1641793924))
					.build();
			Project project2 = Project.builder()
					.name("second name project")
					.description("second description project")
					.supplyVoltage(190.0)
					.supplyFrequency(60.0)
					.maxSlip(1.0)
					.user(user)
					.created(Instant.ofEpochSecond(1641893924))
					.build();

			projectRepository.save(project1);
			projectRepository.save(project2);
	}
}
