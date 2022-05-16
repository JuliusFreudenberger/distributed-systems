package de.hse.distributedsystems.distributedsystems.boundary;

import de.hse.distributedsystems.distributedsystems.database.TodoRepository;
import de.hse.distributedsystems.distributedsystems.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoResource {

    private final TodoRepository todoRepository;

    public TodoResource(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping()
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{todo}")
    public ResponseEntity<Todo> getTodo(@PathVariable final String todo) {
        final var entity = todoRepository.findAllByTodo(todo);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity.get());
    }

    @PostMapping("/")
    public void addTodo(@RequestBody final Todo todo) {
        todoRepository.save(todo);
    }

    @DeleteMapping("/{todo}")
    public void deleteTodo(@PathVariable final String todo) {
        todoRepository.deleteById(todo);
    }
}
