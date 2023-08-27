package app.entities;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
public class Ingredient {

    private final String id;

    private final String name;

    private final Type type;

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
