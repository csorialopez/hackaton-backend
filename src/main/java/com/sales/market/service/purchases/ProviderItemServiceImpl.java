package com.sales.market.service.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.purchases.MeasureUnit;
import com.sales.market.model.purchases.Provider;
import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.ProviderItemRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.service.ItemService;
import com.sales.market.service.ItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProviderItemServiceImpl extends GenericServiceImpl<ProviderItem> implements ProviderItemService {
    private final ProviderItemRepository repository;
    private final ProviderService providerService;
    private final ItemService itemService;
    private final MeasureUnitService measureUnitService;

    @Override
    protected GenericRepository<ProviderItem> getRepository() {
        return repository;
    }

    public ProviderItem getProviderItemsBy(Item item) {
        return repository.getProviderItemsBy(item);
    }

    @Override
    public ProviderItem save(ProviderItem model) {
        Provider provider = providerService.findById(model.getProvider().getId());
        Item item = itemService.findById(model.getItem().getId());
        MeasureUnit measureUnit = measureUnitService.findById(model.getMeasureUnit().getId());

        model.setProvider(provider);
        model.setItem(item);
        model.setMeasureUnit(measureUnit);
        return super.save(model);
    }
}