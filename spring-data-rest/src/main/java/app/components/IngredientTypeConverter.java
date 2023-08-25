package app.components;

import app.entities.Ingredient.Type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@Converter(autoApply=true)
public class IngredientTypeConverter implements AttributeConverter<Type, String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        return type.toString();
    }

    @Override
    public Type convertToEntityAttribute(String type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        return Stream.of(Type.values())
                .filter(x -> x.toString().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
