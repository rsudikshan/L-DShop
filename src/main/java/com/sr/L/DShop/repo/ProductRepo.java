package com.sr.L.DShop.repo;

import com.sr.L.DShop.entities.Categories;
import com.sr.L.DShop.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
}
