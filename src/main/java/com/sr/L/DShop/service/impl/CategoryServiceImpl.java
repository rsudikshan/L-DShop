package com.sr.L.DShop.service.impl;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.Categories;
import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.exceptions.CategoryException;
import com.sr.L.DShop.exceptions.UnauthorizedException;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddCategoryRequest;
import com.sr.L.DShop.payload.Response.CategoryResponse;
import com.sr.L.DShop.repo.CategoryRepo;
import com.sr.L.DShop.repo.UserRepo;
import com.sr.L.DShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;

    @Override
    public ResponseModel addCategory(AddCategoryRequest addCategoryRequest) {

        if(addCategoryRequest.getCategoryName().isEmpty()){
            throw new CategoryException("Category is empty");
        }

        if(categoryRepo.existsByCategoryName(addCategoryRequest.getCategoryName())){
            throw new CategoryException("Category already exists");
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<LdUser> user = userRepo.findByUsername(userDetails.getUsername());

        if(user.isEmpty()){
            throw new UnauthorizedException("Credentials Failure");
        }

        Categories newCategory = dtoToEntityConverterHelper(addCategoryRequest,user.get());
        categoryRepo.save(newCategory);

        return ResponseBuilder.success("Category saved successfully",addCategoryRequest.getCategoryName());
    }

    @Override
    public ResponseModel deleteCategory() {
        return null;
    }

    @Override
    public ResponseModel getAll(){
        return ResponseBuilder.success("category list",categoryRepo.findAll()
                .stream()
                .map(
                     category-> CategoryResponse
                             .builder()
                             .id(category.getId())
                             .categoryName(category.getCategoryName())
                             .build()
                )
                .toList());
    }

    private Categories dtoToEntityConverterHelper(AddCategoryRequest addCategoryRequest , LdUser superAdminId){
        return Categories.builder()
                .createdAt(LocalDateTime.now())
                .categoryName(addCategoryRequest.getCategoryName())
                .addedBy(superAdminId)
                .build();
    }
}
