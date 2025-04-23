package com.sr.L.DShop.service;

import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddProductRequest;
import com.sr.L.DShop.payload.Request.DeleteProductRequest;
import com.sr.L.DShop.payload.Request.UpdateProductRequest;

public interface ProductService {
    ResponseModel addProduct(AddProductRequest addProductRequest);
    ResponseModel getAllProducts();
    ResponseModel getAdminsProducts();
    ResponseModel updateProduct(UpdateProductRequest updateProductRequest) throws NoSuchFieldException,IllegalAccessException;
    ResponseModel deleteProduct(DeleteProductRequest deleteProduct);

}
