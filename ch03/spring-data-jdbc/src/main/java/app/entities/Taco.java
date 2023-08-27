package app.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class Taco {

    @Id
    private Long id;

    private Timestamp createdAt;

    public Taco() {
        Instant instant = Instant.now();
        long milliseconds = instant.toEpochMilli();
        createdAt = new Timestamp(milliseconds);
    }

    @NotNull
    @Size(min=4, message="Name must be at least 4 characters long")
    private String name;

    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<IngredientRef> ingredients = new ArrayList<>();
}
