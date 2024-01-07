package com.dgmf.service;

import com.dgmf.dto.todo.TodoDtoRequest;
import com.dgmf.dto.todo.TodoDtoResponse;

import java.util.List;

public interface TodoService {
    TodoDtoResponse addTodo(TodoDtoRequest todoDtoRequest);
    TodoDtoResponse getTodo(Long todoId);
    List<TodoDtoResponse> getAllTodos();
    TodoDtoResponse updateTodo(Long todoId, TodoDtoRequest todoDtoRequest);
    void deleteTodo(Long todoId);
    TodoDtoResponse CompleteTodo(Long todoId);
    TodoDtoResponse InCompleteTodo(Long todoId);
}
