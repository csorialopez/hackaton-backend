package com.sales.market.service.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.ProviderItemRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.service.ItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProviderItemServiceImpl extends GenericServiceImpl<ProviderItem> implements ProviderItemService {
    private final ProviderItemRepository repository;

    @Override
    protected GenericRepository<ProviderItem> getRepository() {
        return repository;
    }

    public ProviderItem getProviderItemsBy(Item item) {
        return repository.getProviderItemsBy(item);
    }
}