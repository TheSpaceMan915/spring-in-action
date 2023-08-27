package app.repositories;

import app.entities.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder tacoOrder);
}
