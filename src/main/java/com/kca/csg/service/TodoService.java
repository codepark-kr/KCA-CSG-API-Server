package com.kca.csg.service;

import com.kca.csg.model.Todo;
import com.kca.csg.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        Todo entity = Todo.builder().title("My first todo item").build();
        repository.save(entity);
        Todo savedEntity = repository.findById(entity.getId()).get();

        return savedEntity.getTitle();
    }

    public List<Todo> create(final Todo entity) {
        validate(entity);
        repository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final Todo entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<Todo> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<Todo> update(final Todo entity) {
        validate(entity);
        final Optional<Todo> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            repository.save(todo);
        });
        return retrieve(entity.getUserId());
    }


    public List<Todo> delete(final Todo entity) {
        validate(entity);

        try {
        } catch(Exception e) {
            log.error("error deleting entity ", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }
}
