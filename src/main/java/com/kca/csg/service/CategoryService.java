package com.kca.csg.service;

import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Category;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.security.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    PagedResponse<Category> getAllCategories(int page, int size);

    ResponseEntity<Category> getCategory(Long id);

    ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser);

    ResponseEntity<Category> updateCategory(Long id, Category category, UserPrincipal currentUser)
            throws UnauthorizedException;

    ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException;
}
