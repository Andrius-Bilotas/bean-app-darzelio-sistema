
package lt2021.projektas;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ImportResource({ "classpath*:application-context.xml" })
public class AppConfig {
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setUsername("bean.vaidar.mailinformer@gmail.com");
		mailSender.setPassword("slaptazodis");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}