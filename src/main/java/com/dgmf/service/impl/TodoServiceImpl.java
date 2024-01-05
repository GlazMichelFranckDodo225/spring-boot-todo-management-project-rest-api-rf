package com.dgmf.service.impl;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;
import com.dgmf.entity.Todo;
import com.dgmf.exception.ResourceNotFoundException;
import com.dgmf.repository.TodoRepository;
import com.dgmf.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        // Todo todo = todoRepository.findById(todoId).get();
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "Todo Not Found with id : " + todoId
                )
        );
        TodoDtoResponse todoDtoResponse =
                modelMapper.map(todo, TodoDtoResponse.class);

        return todoDtoResponse;
    }

    @Override
    public List<TodoDtoResponse> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoDtoResponse> todoDtoResponses = todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDtoResponse.class))
                .collect(Collectors.toList());

        return todoDtoResponses;
    }

    @Override
    public TodoDtoResponse updateTodo(
            Long todoId, TodoDtoRequest todoDtoRequest
    ) {
        Todo foundTodo = todoRepository.findById(todoId).orElseThrow(() ->
                new ResourceNotFoundException("Todo Not Found with Id : " + todoId)
        );

        foundTodo.setTitle(todoDtoRequest.getTitle());
        foundTodo.setDescription(todoDtoRequest.getDescription());
        foundTodo.setCompleted(todoDtoRequest.isCompleted());

        Todo updatedTodo = todoRepository.save(foundTodo);

        TodoDtoResponse todoDtoResponse = modelMapper.map(
                updatedTodo, TodoDtoResponse.class
        );

        return todoDtoResponse;
    }
}
