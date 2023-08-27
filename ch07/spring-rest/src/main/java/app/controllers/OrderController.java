package app.controllers;

import app.entities.TacoOrder;
import app.repositories.OrderRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/orders",
                produces="application/json")
@CrossOrigin(origins="http://tacocloud:8080")
public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PutMapping(path="/{id}",
                consumes="application/json")
    public TacoOrder putOrder(@PathVariable("id") Long id,
                              @RequestBody TacoOrder tacoOrder) {
        tacoOrder.setId(id);
        return orderRepo.save(tacoOrder);
    }

    @PatchMapping(path="/{id}",
                  consumes="application/json")
    public TacoOrder patchOrder(@PathVariable("id") Long id,
                                @RequestBody TacoOrder patch) {

        TacoOrder order = orderRepo.findById(id).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getCardCvv() != null) {
            order.setCardCvv(patch.getCardCvv());
        }
        if (patch.getCardNumber() != null) {
            order.setCardNumber(patch.getCardNumber());
        }
        if (patch.getCardExpiration() != null) {
            order.setCardExpiration(patch.getCardExpiration());
        }
        return orderRepo.save(order);
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id) {
        try {
            orderRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}
    }
}
