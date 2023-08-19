package app.repositories;

import app.entities.Ingredient;
import app.entities.Taco;
import app.entities.TacoOrder;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

@Slf4j
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepo;

    @Autowired
    private OrderRepository orderRepo;

    private TacoOrder tacoOrder;

    private void loadData() {
        ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientRepo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientRepo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        ingredientRepo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        ingredientRepo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
    }

    @BeforeEach
    public void setup() {
        loadData();
        Ingredient ingredient1 = ingredientRepo.findById("CARN").orElseThrow();
        Ingredient ingredient2 = ingredientRepo.findById("JACK").orElseThrow();
        Ingredient ingredient3 = ingredientRepo.findById("FLTO").orElseThrow();
        Ingredient ingredient4 = ingredientRepo.findById("SLSA").orElseThrow();
        log.info("ingredient1: {}", ingredient1);
        log.info("ingredient2: {}", ingredient2);
        log.info("ingredient3: {}", ingredient3);
        log.info("ingredient4: {}", ingredient4);

        Taco taco1 = new Taco();
        taco1.setName("Test Taco1");
        taco1.addIngredient(ingredient1);
        taco1.addIngredient(ingredient2);

        Taco taco2 = new Taco();
        taco2.setName("Test Taco2");
        taco2.addIngredient(ingredient3);
        taco2.addIngredient(ingredient4);

        tacoOrder = new TacoOrder();
        tacoOrder.setCardCvv("936");
        tacoOrder.setCardNumber("145565");
        tacoOrder.setCardExpiration("02/24");
        tacoOrder.setDeliveryCity("Sydney");
        tacoOrder.setDeliveryName("Nigel");
        tacoOrder.setDeliveryZip("2204");
        tacoOrder.setDeliveryStreet("Shaw Street 56");
        tacoOrder.setDeliveryState("RC");
        tacoOrder.addTaco(taco1);
        tacoOrder.addTaco(taco2);
    }

    @Test
    public void testSave() {
        TacoOrder fetchedOrder = orderRepo.save(tacoOrder);
        assertThat(fetchedOrder.getId()).isNotNull();
        assertThat(fetchedOrder.getDeliveryName()).isEqualTo("Nigel");
        log.info("The test has finished");
    }

    @Test
    @Transactional
    public void testFindByZip() {
        Iterable<TacoOrder> tacoOrders = orderRepo.findAllByDeliveryZip("2204");
        tacoOrders.forEach(order -> log.info("tacoOrder: {}", order));
        assertThat(tacoOrders).isNotEmpty();
    }

    @Test
    @Transactional
    public void testFindPlacedAtBetween() {
        Timestamp startDate = Timestamp.valueOf("2023-04-20 12:30:50");
        Timestamp endDate = Timestamp.valueOf("2023-07-22 23:00:00");
        Iterable<TacoOrder> tacoOrders =
                orderRepo.findAllByDeliveryStateAndPlacedAtBetween(
                        "UK", startDate, endDate);

        tacoOrders.forEach(order -> log.info("tacoOrder: {}", order));
        assertThat(tacoOrders).isNotEmpty();
    }

    @Test
    @Transactional
    public void testFindFromManchester() {
        Iterable<TacoOrder> tacoOrders = orderRepo.findAllFromManchester();
        tacoOrders.forEach(order -> log.info("tacoOrder: {}", order));
        assertThat(tacoOrders).isNotEmpty();
    }
}
