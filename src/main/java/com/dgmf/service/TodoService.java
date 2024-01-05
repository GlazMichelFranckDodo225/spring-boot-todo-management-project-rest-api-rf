package com.dgmf.service;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;

import java.util.List;

public interface TodoService {
    TodoDtoResponse addTodo(TodoDtoRequest todoDtoRequest);
    TodoDtoResponse getTodo(Long todoId);
}
