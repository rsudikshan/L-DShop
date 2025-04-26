package com.sr.L.DShop.repo;

import com.sr.L.DShop.entities.OrderedItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemsRepo extends JpaRepository<OrderedItems,Long> {
}
