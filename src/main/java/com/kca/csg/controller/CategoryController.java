package com.kca.csg.controller;

import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Category;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.security.CurrentUser;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category, @CurrentUser UserPrincipal currentUser){
        return categoryService.addCategory(category, currentUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable(name = "id") Long id){
        return categoryService.getCategory(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Category> updateCategory(@PathVariable(name = "id") Long id,
             @Valid @RequestBody Category category, @CurrentUser UserPrincipal currentUser) throws UnauthorizedException {
        return categoryService.updateCategory(id, category, currentUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(name = "id") Long id,
             @CurrentUser UserPrincipal currentUser) throws UnauthorizedException{
        return categoryService.deleteCategory(id, currentUser);
    }
}
