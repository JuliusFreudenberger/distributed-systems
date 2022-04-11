package de.hse.distributedsystems.distributedsystems.database;

import de.hse.distributedsystems.distributedsystems.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
