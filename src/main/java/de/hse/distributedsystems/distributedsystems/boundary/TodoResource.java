package de.hse.distributedsystems.distributedsystems.boundary;

import de.hse.distributedsystems.distributedsystems.database.TodoRepository;
import de.hse.distributedsystems.distributedsystems.model.Todo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
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
    @Operation(summary = "Get all todos", tags = {"todos"})
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Todo.class)))
    )
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get todo by id", tags = {"todos"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Todo found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Todo.class))
            ),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    public ResponseEntity<Todo> getTodo(
            @PathVariable @Parameter(required = true, description = "todo to get", example = "Clean the kitchen") final String name
    ) {
        final var entity = todoRepository.findAllByTodo(name);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity.get());
    }

    @PostMapping()
    @Operation(summary = "Add a todo", tags = {"todos"})
    @ApiResponse(
            responseCode = "200", description = "Todo was created"
    )
    public void addTodo(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Todo to add") final Todo todo) {
        todoRepository.save(todo);
    }

    @PutMapping("/{name}")
    @Operation(summary = "Modify a todo", tags = {"todos"})
    @ApiResponse(
            responseCode = "200", description = "Todo successfully modified",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Todo.class))
    )
    public ResponseEntity<Object> replaceTodo(
            @PathVariable @Parameter(required = true, description = "todo to modify", example = "Clean the kitchen") final String name,
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Modified todo") final Todo todo
    ) {
        final var persistedTodoOptional = todoRepository.findById(name);
        if (persistedTodoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        final var persistedTodo = persistedTodoOptional.get();
        persistedTodo.setPriority(todo.getPriority());
        final var newlyPersistedTodo = todoRepository.save(persistedTodo);
        return ResponseEntity.ok(newlyPersistedTodo);
    }

    @DeleteMapping("/{name}")
    @Operation(summary = "Delete a todo", tags = {"todos"})
    @ApiResponse(responseCode = "200", description = "Todo successfully deleted")
    public void deleteTodo(@PathVariable @Parameter(required = true, description = "Todo to delete", example = "Clean the kitchen") final String name) {
        todoRepository.deleteById(name);
    }
}
