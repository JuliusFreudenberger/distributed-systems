package de.hse.distributedsystems.distributedsystems.boundary;

import de.hse.distributedsystems.distributedsystems.database.TodoRepository;
import de.hse.distributedsystems.distributedsystems.model.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoResource {

    private final TodoRepository todoRepository;

    public TodoResource(final TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping()
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Todo> getTodo(@PathVariable final String name) {
        final var entity = todoRepository.findAllByTodo(name);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity.get());
    }

    @PostMapping("/")
    public void addTodo(@RequestBody final Todo todo) {
        todoRepository.save(todo);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Object> replaceTodo(@PathVariable final String name, @RequestBody final Todo todo) {
        final var persistedTodoOptional = todoRepository.findById(name);
        if (persistedTodoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        final var persistedTodo = persistedTodoOptional.get();
        persistedTodo.setPriority(todo.getPriority());
        todoRepository.save(persistedTodo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{name}")
    public void deleteTodo(@PathVariable final String name) {
        todoRepository.deleteById(name);
    }
}
