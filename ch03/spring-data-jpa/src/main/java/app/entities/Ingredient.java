package app.entities;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED, force=true)
public class Ingredient {

    @Id
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
