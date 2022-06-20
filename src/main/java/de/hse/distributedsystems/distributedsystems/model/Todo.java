package de.hse.distributedsystems.distributedsystems.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Schema
public class Todo {

    @Id
    @Schema(description = "the todo, must be unique", required = true, example = "Clean the kitchen")
    private String todo;
    @Schema(description = "the priority of the todo", required = true, example = "10")
    private Integer priority;
}
