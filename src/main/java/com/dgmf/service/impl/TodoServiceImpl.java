package com.dgmf.service.impl;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;
import com.dgmf.entity.Todo;
import com.dgmf.repository.TodoRepository;
import com.dgmf.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Override
    public TodoDtoResponse addTodo(TodoDtoRequest todoDtoRequest) {
        Todo todo = modelMapper.map(todoDtoRequest, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        TodoDtoResponse todoDtoResponse = modelMapper.map(
                savedTodo, TodoDtoResponse.class
        );

        return todoDtoResponse;
    }

    @Override
    public TodoDtoResponse getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).get();
        TodoDtoResponse todoDtoResponse =
                modelMapper.map(todo, TodoDtoResponse.class);

        return todoDtoResponse;
    }
}
