package app.entities;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

@Data
public class Ingredient implements Persistable<String> {

    @Id
    private String id;

    private String name;

    private Type type;

    @Override
    public boolean isNew() {
        return true;
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
