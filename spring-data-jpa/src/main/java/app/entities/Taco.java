package app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Timestamp createdAt;

    @NotNull
    @Size(min=4, message="Name must be at least 4 characters long")
    private String name;

    @ManyToMany
        @JoinTable(
            name = "taco_ingredient",
            joinColumns = @JoinColumn(name="taco_id", nullable=false),
            inverseJoinColumns = @JoinColumn(name="ingredient_id", nullable=false))
    @Size(min=1, message="You must choose at least 1 ingredient")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToOne
    private TacoOrder tacoOrder;

    public Taco() {
        Instant instant = Instant.now();
        long milliseconds = instant.toEpochMilli();
        createdAt = new Timestamp(milliseconds);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
