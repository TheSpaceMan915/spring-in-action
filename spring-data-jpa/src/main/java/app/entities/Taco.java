package app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
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
    private List<Ingredient> ingredients = new ArrayList<>();

    public Taco() {
        Instant instant = Instant.now();
        long milliseconds = instant.toEpochMilli();
        createdAt = new Timestamp(milliseconds);
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
