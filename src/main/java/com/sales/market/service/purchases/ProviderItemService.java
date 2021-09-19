package com.sales.market.service.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.service.GenericService;

import java.util.List;

public interface ProviderItemService extends GenericService<ProviderItem> {
    ProviderItem getProviderItemsBy(Item item);
}