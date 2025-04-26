package com.sr.L.DShop.service.impl;

import com.sr.L.DShop.builders.ResponseBuilder;
import com.sr.L.DShop.entities.Categories;
import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.entities.Orders;
import com.sr.L.DShop.entities.Products;
import com.sr.L.DShop.exceptions.CategoryException;
import com.sr.L.DShop.exceptions.ProductException;
import com.sr.L.DShop.exceptions.UnauthorizedException;
import com.sr.L.DShop.models.ResponseModel;
import com.sr.L.DShop.payload.Request.AddProductRequest;
import com.sr.L.DShop.payload.Request.DeleteProductRequest;
import com.sr.L.DShop.payload.Request.UpdateProductRequest;
import com.sr.L.DShop.payload.Response.ProductResponse;
import com.sr.L.DShop.repo.CategoryRepo;
import com.sr.L.DShop.repo.ProductRepo;
import com.sr.L.DShop.repo.UserRepo;
import com.sr.L.DShop.service.ProductService;
import com.sr.L.DShop.utils.PatchHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
        return ResponseBuilder.success("Successful",entityToDtoConverterHelper(productList));
    }

    @Override
    public ResponseModel getAdminsProducts() {
        UserDetails userDetails = this.getAuthenticatedUserDetails();

        //this check is redundant but we cant extract id directly from user details because of super builder
        Optional<LdUser> user = userRepo.findByUsername(userDetails.getUsername());
        if(user.isEmpty()){
            throw new UnauthorizedException("No such user");
        }

        List<Products> productsList = productRepo.findByAdminId_Id(user.get().getId());
        return ResponseBuilder.success("Product List:", entityToDtoConverterHelper(productsList));
    }

    @Override
    public ResponseModel updateProduct(UpdateProductRequest updateProductRequest) throws NoSuchFieldException,IllegalAccessException {
        UserDetails userDetails = getAuthenticatedUserDetails();
        Optional<LdUser> user = userRepo.findByUsername(userDetails.getUsername());
        if(user.isEmpty()){
            throw new UnauthorizedException("No such user");
        }

        Optional<Products> product = productRepo.findById(updateProductRequest.getId());

        if(product.isEmpty()){
            throw new ProductException("No such product");
        }

        Products updatedProduct = product.get();
        LdUser authenticatedUser = user.get();

        if(updatedProduct.getAdminId().getId().equals(authenticatedUser.getId())){
            throw new UnauthorizedException("Product registered with different admin");
        }

        PatchHelper.patchNonNullFields(updateProductRequest,updatedProduct);
        productRepo.save(updatedProduct);
        return ResponseBuilder.success("Update successful",updatedProduct.getProductName());
    }

    @Override
    public ResponseModel deleteProduct(DeleteProductRequest deleteProduct) {

        UserDetails userDetails = getAuthenticatedUserDetails();
        Optional<LdUser> user = userRepo.findByUsername(userDetails.getUsername());

        if(deleteProduct.getId()==null){
            throw new ProductException("Empty Request Body");
        }

        if(user.isEmpty()){
            throw new UnauthorizedException("No such user");
        }

        Optional<Products> products = productRepo.findById(deleteProduct.getId());

        if(products.isEmpty()){
            throw new ProductException("No such product registered");
        }

        LdUser ldUser = user.get();

        if(!Objects.equals(ldUser.getId(), products.get().getAdminId().getId())){
            throw new UnauthorizedException("Product registered with different admin");
        }

        productRepo.deleteById(products.get().getId());

        return ResponseBuilder.success("Delete successful");
    }

    private List<ProductResponse> entityToDtoConverterHelper(List<Products> productList){
        return productList.
                stream()
                .map(product -> {
                    return ProductResponse
                            .builder()
                            .productName(product.getProductName())
                            .productPrice(product.getProductPrice())
                            .productId(product.getId())
                            .categoryName(product.getCategory().getCategoryName())
                            .vendorName(product.getAdminId().getUsername())
                            .imageFileName(null)
                            .build();
                })
                .toList();
    }

//
    private Products dtoToEntityConverterHelper(AddProductRequest addProductRequest,LdUser ldUser,Categories categoryName){
        return Products.builder()
                .productName(addProductRequest.getProductName())
                .productPrice(addProductRequest.getProductPrice())
                .adminId(ldUser)
                .createdAt(LocalDateTime.now())
                .category(categoryName)
                .build();
    }

    private UserDetails getAuthenticatedUserDetails(){
       return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
