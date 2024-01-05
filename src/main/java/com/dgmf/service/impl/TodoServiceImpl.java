package com.dgmf.service.impl;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;
import com.dgmf.entity.Todo;
import com.dgmf.mapper.TodoMapper;
import com.dgmf.repository.TodoRepository;
import com.dgmf.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    public TodoDtoResponse addTodo(TodoDtoRequest todoDtoRequest) {
        Todo todo = TodoMapper.mapToTodo(todoDtoRequest);
        Todo savedTodo = todoRepository.save(todo);
        TodoDtoResponse todoDtoResponse =
                TodoMapper.mapToTodoDtoResponse(savedTodo);

        return todoDtoResponse;
    }
}
