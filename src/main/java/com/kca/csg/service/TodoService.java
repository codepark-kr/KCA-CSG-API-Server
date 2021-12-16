package com.kca.csg.service;

import com.kca.csg.model.Todo;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.security.UserPrincipal;

public interface TodoService {

    Todo completeTodo(Long id, UserPrincipal currentUser);
    Todo unCompleteTodo(Long id, UserPrincipal currentUser);

    PagedResponse<Todo> getAllTodos(UserPrincipal currentUser, int page, int size);

    Todo getTodo(Long id, UserPrincipal currentUser);
    Todo addTodo(Todo todo, UserPrincipal currentUser);
    Todo updateTodo(Long id, Todo newTodo, UserPrincipal currentUser);
    Todo deleteTodo(Long id, UserPrincipal currentUser);
}
