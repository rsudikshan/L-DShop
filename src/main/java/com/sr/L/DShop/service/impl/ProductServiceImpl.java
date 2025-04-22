package com.sr.L.DShop.service.impl;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.Categories;
import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.entities.Products;
import com.sr.L.DShop.exceptions.CategoryException;
import com.sr.L.DShop.exceptions.ProductException;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddProductRequest;
import com.sr.L.DShop.payload.Request.DeleteProduct;
import com.sr.L.DShop.payload.Response.ProductResponse;
import com.sr.L.DShop.repo.CategoryRepo;
import com.sr.L.DShop.repo.ProductRepo;
import com.sr.L.DShop.repo.UserRepo;
import com.sr.L.DShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public ResponseModel addProduct(AddProductRequest addProductRequest) {

        if(addProductRequest.getProductName().isEmpty() || addProductRequest.getProductPrice().isEmpty() || addProductRequest.getCategoryId() == null){
            throw new ProductException("Invalid request body");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Categories> categoryOptional = categoryRepo.findById(addProductRequest.getCategoryId());
        if(categoryOptional.isEmpty()){
            throw new CategoryException("no such category");
        }
        Categories category = categoryOptional.get();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LdUser user = (LdUser) userDetails;
        Products product = dtoToEntityConverterHelper(addProductRequest,user, category);
        productRepo.save(product);
        return ResponseBuilder.success("product saved successfully", "adminId:"+user.getId());
    }

    @Override
    public ResponseModel getAllProducts() {
        List<Products> productList = productRepo.findAll();
        List<ProductResponse> productResponses =
                productList.
                        stream()
                        .map(product -> {
                            return ProductResponse
                                    .builder()
                                    .productName(product.getProductName())
                                    .productPrice(product.getProductPrice())
                                    .categoryName(product.getCategory().getCategoryName())
                                    .vendorName(product.getUserId().getUsername())
                                    .imageFileName(null)
                                    .build();
                        })
                        .toList();


        return ResponseBuilder.success("Successful",productResponses);
    }

    @Override
    public ResponseModel getAdminsProducts() {
        return null;
    }

    @Override
    public ResponseModel updateProduct(AddProductRequest addProductRequest) {
        return null;
    }

    @Override
    public ResponseModel deleteProduct(DeleteProduct deleteProduct) {
        return null;
    }

//
    private Products dtoToEntityConverterHelper(AddProductRequest addProductRequest,LdUser ldUser,Categories categoryName){
        return Products.builder()
                .productName(addProductRequest.getProductName())
                .productPrice(addProductRequest.getProductPrice())
                .userId(ldUser)
                .createdAt(LocalDateTime.now())
                .category(categoryName)
                .build();
    }
}
