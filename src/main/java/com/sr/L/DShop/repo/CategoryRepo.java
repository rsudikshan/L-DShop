package com.sr.L.DShop.repo;

import com.sr.L.DShop.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Categories,Long> {
    Boolean existsByCategoryName(String categoryName);
}
