package com.dgmf.mapper;

import com.dgmf.dto.TodoDtoRequest;
import com.dgmf.dto.TodoDtoResponse;
import com.dgmf.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public static TodoDtoResponse mapToTodoDtoResponse(Todo todo) {
        TodoDtoResponse todoDtoResponse = TodoDtoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .build();

        return todoDtoResponse;
    }

    public static Todo mapToTodo(TodoDtoRequest todoDtoRequest) {
        Todo todo = Todo.builder()
                .title(todoDtoRequest.getTitle())
                .description(todoDtoRequest.getDescription())
                .completed(todoDtoRequest.isCompleted())
                .build();

        return todo;
    }

}
