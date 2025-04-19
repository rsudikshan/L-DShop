package com.sr.L.DShop.service;

import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddCategoryRequest;
import org.apache.coyote.Response;

public interface CategoryService {
    ResponseModel addCategory(AddCategoryRequest addCategoryRequest);
    ResponseModel deleteCategory();
}
