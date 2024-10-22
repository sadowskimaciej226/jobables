package pl.joble;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobablesSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobablesSpringApplication.class, args);
    }

}
