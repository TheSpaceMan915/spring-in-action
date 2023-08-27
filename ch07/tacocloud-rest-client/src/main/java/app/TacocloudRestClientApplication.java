package app;

import app.services.TacoCloudClient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class TacocloudRestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacocloudRestClientApplication.class, args);
    }

    @Bean
    public CommandLineRunner runGetIngredients(TacoCloudClient tacoClient) {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING INGREDIENT BY ID");
            log.info("Ingredient: {}", tacoClient.getIngredientById("SLSA"));
        };
    }
}
