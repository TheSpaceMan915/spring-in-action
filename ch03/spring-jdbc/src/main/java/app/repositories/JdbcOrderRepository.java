package app.repositories;

import app.entities.Ingredient;
import app.entities.Taco;
import app.entities.TacoOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    @Autowired
    private JdbcOperations jdbcOperations;

    private Timestamp getCurrentTime() {
        Instant instant = Instant.now();
        long milliseconds = instant.toEpochMilli();
        return new Timestamp(milliseconds);
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO taco_order "
                        + "(delivery_name, delivery_street, delivery_city, "
                        + "delivery_state, delivery_zip, cc_number, "
                        + "cc_expiration, cc_cvv, placed_at) "
                        + "VALUES (?,?,?,?,?,?,?,?,?)",
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        // Set the time of the order
        tacoOrder.setPlacedAt(getCurrentTime());

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                tacoOrder.getDeliveryName(),
                                tacoOrder.getDeliveryStreet(),
                                tacoOrder.getDeliveryCity(),
                                tacoOrder.getDeliveryState(),
                                tacoOrder.getDeliveryZip(),
                                tacoOrder.getCcNumber(),
                                tacoOrder.getCcExpiration(),
                                tacoOrder.getCcCVV(),
                                tacoOrder.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        // long orderId = keyHolder.getKey().longValue();
        long orderId = Long.parseLong(keyHolder.getKeys().get("id").toString());
        tacoOrder.setId(orderId);

        List<Taco> tacos = tacoOrder.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }

        return tacoOrder;
    }

    private long saveTaco(long orderId, int tacoIndex, Taco taco) {
        taco.setCreatedAt(getCurrentTime());

        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO taco "
                        + "(name, created_at, taco_order_id, index) "
                        + "VALUES (?, ?, ?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.INTEGER
                );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                taco.getName(),
                                taco.getCreatedAt(),
                                orderId,
                                tacoIndex));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        // long tacoId = keyHolder.getKey().longValue();
        long tacoId = Long.parseLong(keyHolder.getKeys().get("id").toString());
        taco.setId(tacoId);

        int i = 0;
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientRef(tacoId, i++, ingredient);
        }

        return tacoId;
    }

    private void saveIngredientRef(long tacoId, long ingredientIndex, Ingredient ingredient) {
        jdbcOperations.update(
                "INSERT INTO ingredient_ref "
                + "(taco_id, ingredient_id, ingredient_index) "
                + "VALUES (?, ?, ?)",
                tacoId,
                ingredient.getId(),
                ingredientIndex);
    }
}
