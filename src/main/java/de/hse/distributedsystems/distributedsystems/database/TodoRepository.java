package de.hse.distributedsystems.distributedsystems.database;

import de.hse.distributedsystems.distributedsystems.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, String> {

    Optional<Todo> findAllByTodo(String todo);

}
