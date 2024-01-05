package com.dgmf.controller;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;
import com.dgmf.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
