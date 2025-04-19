package com.sr.L.DShop.controllers;

import com.sr.L.DShop.annotations.CategoryController;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddCategoryRequest;
import com.sr.L.DShop.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CategoryController
@RequiredArgsConstructor
public class CategoriesController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/super/addCategory")
    public ResponseEntity<ResponseModel> addCategory(@RequestBody AddCategoryRequest addCategoryRequest){
        return ResponseEntity.ok().body(categoryService.addCategory(addCategoryRequest));
    }


    @GetMapping("/getAllCategories")
    public ResponseEntity<ResponseModel> getAll(){
        return ResponseEntity.ok().body(categoryService.getAll());
    }
}
