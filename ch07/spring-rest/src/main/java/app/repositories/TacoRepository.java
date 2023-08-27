package app.repositories;

import app.entities.Taco;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository extends
        CrudRepository<Taco, Long>,
        PagingAndSortingRepository<Taco, Long> {

}
