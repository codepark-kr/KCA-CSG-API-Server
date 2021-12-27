package com.kca.csg.service.impl;

import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Todo;
import com.kca.csg.model.User;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.repository.TodoRepository;
import com.kca.csg.repository.UserRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.Constants.*;
import static com.kca.csg.util.GlobalUtils.getResourceById;
import static com.kca.csg.util.GlobalUtils.sortDescending;
import static com.kca.csg.util.ValidationUtils.noPermissionResponse;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

/**     TODO: Same or similar statement for 'validate some object/user' is repeating too much.
*             It must be extract to another integrated-method, or commonUtil class.
*             (last update: 12.16.2021.) */
    @Override
    public Todo completeTodo(Long id, UserPrincipal currentUser){
        Todo todo = (Todo) getResourceById(id, TODO, id);

        assert todo != null;
        if(isByCurrentUser(todo, userRepository.getUser(currentUser))){
            todo.setCompleted(Boolean.TRUE);
            return todoRepository.save(todo);
        }
        throw new UnauthorizedException(noPermissionResponse());
    }

/**     TODO: This conditional-statement as well.
*             It's related to validation for currentUser@UserPrincipal as well.
*             (last update: 12.16.2021.) */
    @Override
    public Todo unCompleteTodo(Long id, UserPrincipal currentUser) {
        Todo todo = (Todo) getResourceById(id, TODO, id);

        assert todo!= null;
        if(isByCurrentUser(todo, userRepository.getUser(currentUser))){
            todo.setCompleted(Boolean.TRUE);
            return todoRepository.save(todo);
        }
        throw new UnauthorizedException(noPermissionResponse());
    }

    @Override
    public PagedResponse<Todo> getAllTodos(UserPrincipal currentUser, int page, int size) {
        Page<Todo> todos = todoRepository.findByCreatedBy(currentUser.getId(), sortDescending(page, size));
        List<Todo> content = todos.getNumberOfElements() == 0 ? Collections.emptyList() : todos.getContent();

        return new PagedResponse<>(content, todos.getNumber(), todos.getSize(), todos.getTotalElements(), todos.getTotalPages(), todos.isLast());
    }

    @Override
    public Todo getTodo(Long id, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        Todo todo = (Todo) getResourceById(id, TODO, id);

        assert todo != null;
        if(isByCurrentUser(todo, user)){ return todo; }

        throw new UnauthorizedException(noPermissionResponse());
    }

    @Override
    public Todo addTodo(Todo todo, UserPrincipal currentUser) {
        todo.setUser(userRepository.getUser(currentUser));

        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo newTodo, UserPrincipal currentUser) {
        Todo todo = (Todo) getResourceById(id, TODO, id);

        assert todo != null;
        if(isByCurrentUser(todo, userRepository.getUser(currentUser))){
            todo.setTitle(newTodo.getTitle());
            todo.setCompleted(newTodo.getCompleted());
        return todoRepository.save(todo);
        }
        throw new UnauthorizedException(noPermissionResponse());
    }

    @Override
    public ApiResponse deleteTodo(Long id, UserPrincipal currentUser) {
        Todo todo = (Todo) getResourceById(id, TODO, id);

        assert todo != null;
        if(isByCurrentUser(todo, userRepository.getUser(currentUser))){
            todoRepository.deleteById(id);
            return new ApiResponse(Boolean.TRUE, SUCCESS_DELETE + "todo");
        }
        throw new UnauthorizedException(noPermissionResponse());
    }

    public Boolean isByCurrentUser(Todo todo, User user){ return todo.getUser().getId().equals(user.getId()); }
}
