package lt2021.projektas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lt2021.projektas.kindergarten.admission.AdmissionDao;
import lt2021.projektas.kindergarten.admission.AdmissionProcess;
import lt2021.projektas.userRegister.User;
import lt2021.projektas.userRegister.UserDao;
import lt2021.projektas.userRegister.UserRole;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class App extends SpringBootServletInitializer {
	
	@Bean
	public Docket swaggerDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("lt2021.projektas")).build();
	}
	
	@Bean
	public CommandLineRunner SeedDatabase(UserDao userDao, AdmissionDao admissionDao) {
		return (args) -> {
			var users = userDao.findAll();
			
			if (!(users.stream().anyMatch(u -> u.getRole().equals(UserRole.ADMIN)))) {
				User admin = new User("admin", "admin", "admin@test.lt", UserRole.ADMIN);
				PasswordEncoder encoder = new MessageDigestPasswordEncoder("SHA-256");
				admin.setPassword(encoder.encode("admin"));
				userDao.save(admin);
			}
			if (admissionDao.findAll().size() == 0) {
				AdmissionProcess admission = new AdmissionProcess();
				admissionDao.save(admission);
			}
		};
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("IT Akademija REST Documentation").version("0.0.1-SNAPSHOT").build();
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
}