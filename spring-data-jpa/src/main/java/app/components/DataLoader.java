package app.components;

import app.entities.Ingredient;
import app.entities.Ingredient.Type;
import app.repositories.IngredientRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final IngredientRepository ingredientRepo;
//
//    public DataLoader(IngredientRepository ingredientRepo) {
//        this.ingredientRepo = ingredientRepo;
//    }
//
//    @Override
//    public void run(String... args) {
//        ingredientRepo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//        ingredientRepo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//        ingredientRepo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//        ingredientRepo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//        ingredientRepo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//        ingredientRepo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//        ingredientRepo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
//        ingredientRepo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//        ingredientRepo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
//        ingredientRepo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//    }
//}
