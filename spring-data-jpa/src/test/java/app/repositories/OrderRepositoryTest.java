package app.repositories;

import app.entities.Ingredient;
import app.entities.Taco;
import app.entities.TacoOrder;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class OrderRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepo;

    @Autowired
    private OrderRepository orderRepository;

    private TacoOrder tacoOrder;

    @BeforeEach
    public void setup() {
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
    public void testOrderRepository() {
        TacoOrder fetchedOrder = orderRepository.save(tacoOrder);
        log.info("tacoOrder: {}", fetchedOrder);
        assertThat(fetchedOrder.getId()).isNotNull();
        assertThat(fetchedOrder.getDeliveryName()).isEqualTo("Nigel");
    }
}
