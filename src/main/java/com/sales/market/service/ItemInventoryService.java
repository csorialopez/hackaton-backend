package com.sales.market.service;

import com.sales.market.model.ItemInventory;

import java.util.List;

public interface ItemInventoryService extends GenericService<ItemInventory> {
    List<ItemInventory> getItemsLowerBoundery();
}
