package com.kca.csg.service.impl;

import com.kca.csg.exception.UnauthorizedException;
import com.kca.csg.model.Category;
import com.kca.csg.model.role.RoleName;
import com.kca.csg.payload.response.ApiResponse;
import com.kca.csg.payload.response.PagedResponse;
import com.kca.csg.repository.CategoryRepository;
import com.kca.csg.security.UserPrincipal;
import com.kca.csg.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.kca.csg.util.Constants.*;
import static com.kca.csg.util.GlobalUtils.findResourceById;
import static com.kca.csg.util.GlobalUtils.sortDescending;
import static com.kca.csg.util.ValidationUtils.pageValidation;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PagedResponse<Category> getAllCategories(int page, int size) {
        pageValidation(page, size);
        Page<Category> categories = categoryRepository.findAll(sortDescending(page, size));
        List<Category> content = categories.getNumberOfElements() == 0 ? Collections.emptyList() : categories.getContent();

        return new PagedResponse<>(content, categories.getNumber(), categories.getSize(), categories.getTotalElements(),
                categories.getTotalPages(), categories.isLast());
    }

    @Override
    public ResponseEntity<Category> getCategory(Long id) {
        return new ResponseEntity<>((Category) findResourceById(id, CATEGORY, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser) {
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long id, Category category, UserPrincipal currentUser) throws UnauthorizedException {
        Category newCategory = (Category) findResourceById(id, CATEGORY, id);

        assert newCategory != null;
        if(category.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            category.setName(newCategory.getName());
            return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
        }
        throw new UnauthorizedException(NO_PERMISSION_TO + "edit this category");
    }

    @Override
    public ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException {
        Category category = (Category) findResourceById(id, CATEGORY, id);

        assert category != null;
        if(category.getCreatedBy().equals(currentUser.getId()) || currentUser.getAuthorities()
                .contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, SUCCESS_DELETE + "category"), HttpStatus.OK);
        }
        throw new UnauthorizedException(NO_PERMISSION_TO + "delete this category");
    }
}
