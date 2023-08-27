package app.entities;

import jakarta.validation.constraints.*;

import lombok.Data;

import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder implements Serializable {

    private long id;

    private Timestamp placedAt;

    @NotBlank(message="Delivery name is required")
    private String deliveryName;

    @NotBlank(message="Delivery street is required")
    private String deliveryStreet;

    @NotBlank(message="Delivery city is required")
    private String deliveryCity;

    @NotBlank(message="Delivery state is required")
    private String deliveryState;

    @NotBlank(message="Delivery zip is required")
    private String deliveryZip;

    @CreditCardNumber(message="Credit card number must be valid")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
    message="Expiration date must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
