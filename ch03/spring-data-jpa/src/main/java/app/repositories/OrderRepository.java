package app.repositories;

import app.entities.TacoOrder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    Iterable<TacoOrder> findAllByDeliveryZip(String deliveryZip);

    Iterable<TacoOrder> findAllByDeliveryStateAndPlacedAtBetween(
            String deliveryState, Timestamp startDate, Timestamp endDate);

    @Query("SELECT o FROM TacoOrder o WHERE o.deliveryCity='Manchester'")
    Iterable<TacoOrder> findAllFromManchester();
}
