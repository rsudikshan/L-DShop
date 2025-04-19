package com.sr.L.DShop.service;

import com.sr.L.DShop.entities.Products;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddProductRequest;
import com.sr.L.DShop.payload.Request.DeleteProduct;
import org.apache.catalina.connector.Response;

public interface ProductService {
    ResponseModel addProduct(AddProductRequest addProductRequest);
    ResponseModel getAllProducts();
    ResponseModel getAdminsProducts();
    ResponseModel updateProduct(AddProductRequest addProductRequest);
    ResponseModel deleteProduct(DeleteProduct deleteProduct);

}
