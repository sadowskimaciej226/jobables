package pl.joble;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.joble.infrastructure.security.JwtConfigurationProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(value = {JwtConfigurationProperties.class})
public class JobablesSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobablesSpringApplication.class, args);
    }

}
