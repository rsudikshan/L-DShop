package com.sr.L.DShop.controllers;

import com.sr.L.DShop.annotations.ProductController;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddProductRequest;
import com.sr.L.DShop.payload.Request.UpdateProductRequest;
import com.sr.L.DShop.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@ProductController
public class ProductsController {

    private final ProductServiceImpl productService;


    @PostMapping("/admin/addProduct")
    public ResponseEntity<ResponseModel> addProduct(@RequestBody AddProductRequest addProductRequest){
        return ResponseEntity.ok().body(productService.addProduct(addProductRequest));
    }

    @PostMapping("/admin/updateProduct")
    public ResponseEntity<ResponseModel> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) throws NoSuchFieldException, IllegalAccessException {
        return ResponseEntity.ok().body(productService.updateProduct(updateProductRequest));
    }

    @GetMapping("/admin/getAll")
    public ResponseEntity<ResponseModel> getAll(){
        return ResponseEntity.ok().body(productService.getAdminsProducts());
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseModel> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
}
