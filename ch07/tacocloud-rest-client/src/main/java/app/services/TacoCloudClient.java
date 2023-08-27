package app.services;

import app.entities.Ingredient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TacoCloudClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject(
                "http://192.168.1.65:8080/data-api/ingredients/{id}",
                Ingredient.class, ingredientId);
    }
}
