package com.dgmf.service;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;
import com.dgmf.entity.Todo;

import java.util.List;

public interface TodoService {
    TodoDtoResponse addTodo(TodoDtoRequest todoDtoRequest);
    TodoDtoResponse getTodo(Long todoId);
    List<TodoDtoResponse> getAllTodos();
    TodoDtoResponse updateTodo(Long todoId, TodoDtoRequest todoDtoRequest);
    void deleteTodo(Long todoId);
}
