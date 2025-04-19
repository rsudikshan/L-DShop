package com.sr.L.DShop.repo;

import com.sr.L.DShop.entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Categories,Long> {
    Boolean existsByCategoryName(String categoryName);
    //Optional<Categories> findById(Long id);
    //List<Categories> findAll();
}
