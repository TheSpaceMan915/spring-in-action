package app.components;

import app.entities.Ingredient;
import app.repositories.IngredientRepository;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    public IngredientConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
