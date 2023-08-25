package app.components;

import app.entities.Ingredient;
import app.entities.Ingredient.Type;
import app.entities.Taco;
import app.entities.TacoOrder;
import app.repositories.IngredientRepository;
import app.repositories.OrderRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DataLoader implements CommandLineRunner {

    private final IngredientRepository ingredientRepo;

    private final OrderRepository orderRepo;

    public DataLoader(IngredientRepository ingredientRepo, OrderRepository orderRepo) {
        this.ingredientRepo = ingredientRepo;
        this.orderRepo = orderRepo;
    }

    private void saveIngredients() {
        ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        ingredientRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        ingredientRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        ingredientRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        ingredientRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    }

    private void saveOrders() {
        Ingredient ingredient1 = ingredientRepo.findById("CARN").orElseThrow();
        Ingredient ingredient2 = ingredientRepo.findById("JACK").orElseThrow();
        Ingredient ingredient3 = ingredientRepo.findById("FLTO").orElseThrow();
        Ingredient ingredient4 = ingredientRepo.findById("SLSA").orElseThrow();

        Taco taco1 = new Taco();
        taco1.setName("Test Taco1");
        taco1.setCreatedAt(
                Timestamp.valueOf("2023-03-03 08:10:00"));
        taco1.addIngredient(ingredient1);
        taco1.addIngredient(ingredient2);

        Taco taco2 = new Taco();
        taco2.setName("Test Taco2");
        taco2.setCreatedAt(
                Timestamp.valueOf("2023-04-11 13:30:00"));
        taco2.addIngredient(ingredient3);
        taco2.addIngredient(ingredient4);

        Taco taco3 = new Taco();
        taco3.setName("Test Taco3");
        taco3.setCreatedAt(
                Timestamp.valueOf("2023-08-26 18:15:00"));
        taco3.addIngredient(ingredient1);
        taco3.addIngredient(ingredient3);

        TacoOrder tacoOrder1 = new TacoOrder();
        tacoOrder1.setCardCvv("936");
        tacoOrder1.setCardNumber("145565");
        tacoOrder1.setCardExpiration("02/24");
        tacoOrder1.setDeliveryCity("Sydney");
        tacoOrder1.setDeliveryName("Nigel");
        tacoOrder1.setDeliveryZip("2204");
        tacoOrder1.setDeliveryStreet("Shaw Street 56");
        tacoOrder1.setDeliveryState("RC");
        tacoOrder1.addTaco(taco1);
        tacoOrder1.addTaco(taco2);
        orderRepo.save(tacoOrder1);

        TacoOrder tacoOrder2 = new TacoOrder();
        tacoOrder2.setCardCvv("125");
        tacoOrder2.setCardNumber("145565");
        tacoOrder2.setCardExpiration("04/21");
        tacoOrder2.setDeliveryCity("Manchester");
        tacoOrder2.setDeliveryName("Carlos");
        tacoOrder2.setDeliveryZip("3052");
        tacoOrder2.setDeliveryStreet("Stanley Grove 2");
        tacoOrder2.setDeliveryState("CJ");
        tacoOrder2.addTaco(taco3);
        orderRepo.save(tacoOrder2);
    }

    @Override
    public void run(String... args) {
        saveIngredients();
        saveOrders();
    }
}