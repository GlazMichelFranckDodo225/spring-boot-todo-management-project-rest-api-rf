package com.dgmf.service;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;

public interface TodoService {
    TodoDtoResponse addTodo(TodoDtoRequest todoDtoRequest);
}
