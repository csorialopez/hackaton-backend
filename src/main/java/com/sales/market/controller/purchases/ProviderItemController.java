package com.sales.market.controller.purchases;

import com.sales.market.controller.GenericController;
import com.sales.market.dto.purchases.ProviderItemDto;
import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.service.GenericService;

import com.sales.market.service.purchases.ProviderItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/provideritems")
public class ProviderItemController extends GenericController<ProviderItem, ProviderItemDto> {
    private ProviderItemService service;


    @Override
    protected GenericService getService() {
        return service;
    }
}
