package app.controllers;

import app.entities.Ingredient;
import app.entities.Ingredient.Type;
import app.repositories.IngredientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepo;

    private List<Ingredient> ingredients;

    @BeforeEach
    public void setup() {
        ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        when(ingredientRepo.findAll()).thenReturn(ingredients);

        when(ingredientRepo.findById("COTO"))
                .thenReturn(Optional.of(new Ingredient("COTO", "Corn Tortilla", Type.WRAP)));
        when(ingredientRepo.findById("CARN"))
                .thenReturn(Optional.of(new Ingredient("CARN", "Carnitas", Type.PROTEIN)));
        when(ingredientRepo.findById("JACK"))
                .thenReturn(Optional.of(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE)));
    }

    @Test
    public void testDesignPage() throws Exception {
        mockMvc.perform(get("/design"))
                .andExpect(status().isOk())
                .andExpect(view().name("design"))
                .andExpect(model().attribute(
                        "wrap", ingredients.subList(0, 2)))
                .andExpect(model().attribute(
                        "protein", ingredients.subList(2, 4)))
                .andExpect(model().attribute(
                        "veggies", ingredients.subList(4, 6)))
                .andExpect(model().attribute(
                        "cheese", ingredients.subList(6, 8)))
                .andExpect(model().attribute(
                        "sauce", ingredients.subList(8, 10)));
    }

    @Test
    public void testProcessTaco() throws Exception {
        mockMvc.perform(post("/design"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("Design your taco!")))
                .andExpect(content().contentType(
                        MediaType.valueOf("text/html;charset=UTF-8")));
    }
}
