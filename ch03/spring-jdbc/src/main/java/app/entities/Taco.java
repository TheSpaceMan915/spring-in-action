package app.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Taco {

    private long id;

    private Timestamp createdAt;

    @NotNull
    @Size(min=4, message="Name must be at least 4 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
