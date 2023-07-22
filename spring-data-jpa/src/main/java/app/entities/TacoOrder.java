package app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class TacoOrder implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

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
    private String cardNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
    message="Expiration date must be formatted MM/YY")
    private String cardExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String cardCvv;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "taco_order_taco",
            joinColumns = @JoinColumn(name="taco_order_id", nullable=false),
            inverseJoinColumns = @JoinColumn(name="taco_id", nullable=false))
    private List<Taco> tacos = new ArrayList<>();

    public TacoOrder() {
        Instant instant = Instant.now();
        long milliseconds = instant.toEpochMilli();
        placedAt = new Timestamp(milliseconds);
    }

    @Override
    public String toString() {
        return "TacoOrder{" +
                "id=" + id +
                ", placedAt=" + placedAt +
                ", deliveryName='" + deliveryName + '\'' +
                ", deliveryStreet='" + deliveryStreet + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryZip='" + deliveryZip + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiration='" + cardExpiration + '\'' +
                ", cardCvv='" + cardCvv + '\'' +
                ", tacos=" + tacos +
                '}';
    }

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}
