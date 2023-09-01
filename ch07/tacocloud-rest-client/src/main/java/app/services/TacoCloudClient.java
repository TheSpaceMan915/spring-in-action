package app.services;

import app.entities.Ingredient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TacoCloudClient {

    private final RestTemplate restTemplate;
    private final String rootUrl = "http://192.168.1.65:8080/data-api/ingredients";

    public TacoCloudClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject(
                rootUrl + "/{id}",
                Ingredient.class,
                ingredientId);
    }

//    public Ingredient getIngredientById(String ingredientId) {
//        Map<String, String> urlVariables = new HashMap<>();
//        urlVariables.put("id", ingredientId);
//        ResponseEntity<Ingredient> responseEntity =
//                restTemplate.getForEntity(
//                        rootUrl,
//                        Ingredient.class,
//                        urlVariables
//                );
//        log.info("FETCHED TIME: {}",
//                new Date(responseEntity.getHeaders().getDate()));
//        log.info("RESPONSE STATUS: {}",
//                responseEntity.getStatusCode());
//        return responseEntity.getBody();
//    }

    public void putIngredient(Ingredient ingredient) {
        restTemplate.put(
                rootUrl + "/{id}",
                ingredient,
                ingredient.getId());
    }

//    public void putIngredient(Ingredient ingredient) {
//        URI url = UriComponentsBuilder
//                .fromHttpUrl(rootUrl + "/{id}")
//                .build(ingredient.getId());
//        restTemplate.put(url, ingredient);
//    }

    public void deleteIngredientById(String ingredientId) {
        restTemplate.delete(
                rootUrl + "/{id}",
                ingredientId);
    }

    public Ingredient postIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(
                rootUrl,
                ingredient,
                Ingredient.class);
    }

//    public Ingredient postIngredient(Ingredient ingredient) {
//        ResponseEntity<Ingredient> responseEntity =
//                restTemplate.postForEntity(
//                        rootUrl,
//                        ingredient,
//                        Ingredient.class
//                );
//        log.info("CREATED TIME: {}",
//                new Date(responseEntity.getHeaders().getDate()));
//        log.info("RESPONSE STATUS: {}",
//                responseEntity.getStatusCode());
//        return responseEntity.getBody();
//    }
}
