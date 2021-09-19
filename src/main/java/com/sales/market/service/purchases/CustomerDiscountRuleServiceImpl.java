package com.sales.market.service.purchases;

import com.sales.market.model.purchases.CustomerDiscountRule;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.CustomerDiscountRuleRepository;
import com.sales.market.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerDiscountRuleServiceImpl extends GenericServiceImpl<CustomerDiscountRule> implements CustomerDiscountRuleService{
    private CustomerDiscountRuleRepository repository;

    @Override
    protected GenericRepository<CustomerDiscountRule> getRepository() {
        return repository;
    }
}
