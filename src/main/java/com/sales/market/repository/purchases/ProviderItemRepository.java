package com.sales.market.repository.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.repository.GenericRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProviderItemRepository extends GenericRepository<ProviderItem> {
    @Query("select min(provider.price) from ProviderItem provider where provider.item=:item ")
    ProviderItem getProviderItemsBy(@Param("item") Item item);


}