package com.dgmf.controller;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;
import com.dgmf.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // Add Todo REST API
    @PostMapping
    public ResponseEntity<TodoDtoResponse> addTodo(
            @RequestBody TodoDtoRequest todoDtoRequest
    ) {
        return new ResponseEntity<>(
                todoService.addTodo(todoDtoRequest),
                HttpStatus.CREATED
        );
    }

    // Get Todo REST API
    @GetMapping("/{id}")
    public ResponseEntity<TodoDtoResponse> getTodo(
            @PathVariable("id") Long todoId
    ) {
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }

    // Get All Todos REST API
    @GetMapping
    public ResponseEntity<List<TodoDtoResponse>> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    // Update Todo REST API
    @PutMapping("{id}")
    public ResponseEntity<TodoDtoResponse> updateTodo(
            @PathVariable("id") Long todoId,
            @RequestBody TodoDtoRequest todoDtoRequest
    ) {
        return ResponseEntity.ok(
                todoService.updateTodo(todoId, todoDtoRequest)
        );
    }
}
