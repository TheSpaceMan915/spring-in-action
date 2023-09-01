package app;

import app.entities.Ingredient;
import app.services.TacoCloudClient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class TacocloudRestClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacocloudRestClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner runGetIngredient(TacoCloudClient tacoClient) {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("GETTING INGREDIENT BY ID");
            Ingredient ingredient = tacoClient.getIngredientById("TMTO");
            log.info("Ingredient: {}", ingredient);
        };
    }

    @Bean
    public CommandLineRunner runPutIngredient(TacoCloudClient tacoClient) {
        return args -> {
            log.info("----------------------- PUT -------------------------");
            log.info("PUTTING INGREDIENT");
            Ingredient before = tacoClient.getIngredientById("FLTO");
            log.info("BEFORE: {}", before);
            tacoClient.putIngredient(
                    new Ingredient("FLTO", "Really Tasty Tortilla", Ingredient.Type.WRAP));
            Ingredient after = tacoClient.getIngredientById("FLTO");
            log.info("AFTER: {}", after);
        };
    }

    @Bean
    public CommandLineRunner runDeleteIngredient(TacoCloudClient tacoClient) {
        return args -> {
            log.info("----------------------- DELETING -------------------------");
            log.info("CREATING AN INGREDIENT TO BE DELETED");
            Ingredient ingredient = new Ingredient("ONIO", "Onion", Ingredient.Type.VEGGIES);
            tacoClient.postIngredient(ingredient);
            log.info("BEFORE: {}", ingredient);
            log.info("DELETING INGREDIENT");
            tacoClient.deleteIngredientById(ingredient.getId());
//            Ingredient after = tacoClient.getIngredientById("ONIO");
//            log.info("AFTER: {}", after);
        };
    }

    @Bean
    public CommandLineRunner runPostIngredient(TacoCloudClient tacoClient) {
        return args -> {
            log.info("----------------------- POSTING -------------------------");
            log.info("CREATING AN INGREDIENT");
            Ingredient ingredient = new Ingredient("OEPA", "Old El Paso", Ingredient.Type.SAUCE);
            Ingredient created = tacoClient.postIngredient(ingredient);
            log.info("CREATED INGREDIENT: {}", created);
        };
    }
}
