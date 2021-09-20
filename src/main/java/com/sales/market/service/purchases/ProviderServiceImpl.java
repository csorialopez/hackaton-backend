package com.sales.market.service.purchases;

import com.sales.market.model.purchases.Provider;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.ProviderRepository;
import com.sales.market.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ProviderServiceImpl extends GenericServiceImpl<Provider> implements ProviderService {
    private final ProviderRepository repository;

    @Override
    protected GenericRepository<Provider> getRepository() {
        return repository;
    }

}