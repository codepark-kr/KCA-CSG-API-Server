package com.kca.csg.service.impl;

import com.kca.csg.exception.ResourceNotFoundException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.AppConstants.*;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Todo completeTodo(Long id, UserPrincipal currentUser){
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));
        User user = userRepository.getUser(currentUser);
/**     TODO: Same or similar statement for 'validate some object/user' is repeating too much.
*            It must be extract to another integrated-method, or commonUtil class.
*            (last update: 12.16.2021.) */

        if(todo.getUser().getId().equals((user.getId()))){
            todo.setCompleted(Boolean.TRUE);
            return todoRepository.save(todo);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION);
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public Todo unCompleteTodo(Long id, UserPrincipal currentUser) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));
        User user = userRepository.getUser(currentUser);

        if(todo.getUser().getId().equals(user.getId())){
/**     TODO: This conditional-statement as well.
*             It's related to validation for currentUser@UserPrincipal as well.
*            (last update: 12.16.2021.) */
            todo.setCompleted(Boolean.TRUE);
            return todoRepository.save(todo);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION);
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public PagedResponse<Todo> getAllTodos(UserPrincipal currentUser, int page, int size) {
        pageValidation(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<Todo> todos = todoRepository.findByCreatedBy(currentUser.getId(), pageable);
        List<Todo> content = todos.getNumberOfElements() == 0 ? Collections.emptyList() : todos.getContent();

        return new PagedResponse<>(content, todos.getNumber(), todos.getSize(), todos.getTotalElements(), todos.getTotalPages(), todos.isLast());
    }

    @Override
    public Todo getTodo(Long id, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));

        if(todo.getUser().getId().equals(user.getId())){ return todo; }

        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION);
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public Todo addTodo(Todo todo, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(Long id, Todo newTodo, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));

        if(todo.getUser().getId().equals(user.getId())){
            todo.setTitle(newTodo.getTitle());
            todo.setCompleted(newTodo.getCompleted());
        return todoRepository.save(todo);
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION);
        throw new UnauthorizedException(apiResponse);
    }

    @Override
    public ApiResponse deleteTodo(Long id, UserPrincipal currentUser) {
        User user = userRepository.getUser(currentUser);
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));

        if(todo.getUser().getId().equals(user.getId())){
            todoRepository.deleteById(id);
            return new ApiResponse(Boolean.TRUE, "You successfully deleted todo");
        }
        ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, NO_PERMISSION);
        throw new UnauthorizedException(apiResponse);
    }
}
