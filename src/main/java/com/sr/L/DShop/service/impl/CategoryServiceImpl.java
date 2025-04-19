package com.sr.L.DShop.service.impl;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.Categories;
import com.sr.L.DShop.exceptions.CategoryException;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddCategoryRequest;
import com.sr.L.DShop.payload.Response.CategoryResponse;
import com.sr.L.DShop.repo.CategoryRepo;
import com.sr.L.DShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public ResponseModel addCategory(AddCategoryRequest addCategoryRequest) {

        if(categoryRepo.existsByCategoryName(addCategoryRequest.getCategoryName())){
            throw new CategoryException("Category already exists");
        }
        categoryRepo.save(dtoToEntityConverterHelper(addCategoryRequest));
        return ResponseBuilder.success("Category save successfully",addCategoryRequest.getCategoryName());
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

    private Categories dtoToEntityConverterHelper(AddCategoryRequest addCategoryRequest){
        return Categories.builder()
                .createdAt(LocalDateTime.now())
                .categoryName(addCategoryRequest.getCategoryName())
                .build();
    }
}
