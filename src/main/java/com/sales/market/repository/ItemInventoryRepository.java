package com.sales.market.repository;

import com.sales.market.model.ItemInventory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemInventoryRepository extends GenericRepository<ItemInventory> {
    @Query("SELECT items FROM ItemInventory items"
            +" WHERE items.lowerBoundThreshold <= items.stockQuantity")
    List<ItemInventory> geItemsLowerBoundery();
}
