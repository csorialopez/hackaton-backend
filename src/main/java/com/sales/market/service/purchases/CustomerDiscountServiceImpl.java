package com.sales.market.service.purchases;

import com.sales.market.model.purchases.CustomerDiscount;
import com.sales.market.model.purchases.CustomerDiscountRule;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.CustomerDiscountRepository;
import com.sales.market.repository.purchases.CustomerDiscountRuleRepository;
import com.sales.market.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerDiscountServiceImpl extends GenericServiceImpl<CustomerDiscount> implements CustomerDiscountService{
    private CustomerDiscountRepository repository;

    @Override
    protected GenericRepository<CustomerDiscount> getRepository() {
        return repository;
    }

}
