package pl.wsb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "pl.wsb.fitnesstracker")
@EnableJpaRepositories(basePackages = "pl.wsb.fitnesstracker")
@SpringBootApplication
public class FitnessTracker {

    public static void main(String[] args) {
        SpringApplication.run(FitnessTracker.class, args);
        System.out.println("Link do aplikacji: http://localhost:8091/h2-console");
        System.out.println("zmiana by zrobić push3");
    }

}
