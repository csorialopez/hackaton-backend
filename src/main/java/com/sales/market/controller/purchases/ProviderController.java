package com.sales.market.controller.purchases;

import com.sales.market.controller.GenericController;
import com.sales.market.dto.purchases.ProviderDto;
import com.sales.market.model.purchases.Provider;
import com.sales.market.service.GenericService;
import com.sales.market.service.purchases.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/providers")
public class ProviderController extends GenericController<Provider, ProviderDto> {
    private ProviderService service;

    @Override
    protected GenericService getService() {
        return service;
    }
}
