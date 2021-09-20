package com.sales.market.service.purchases;

import com.sales.market.model.purchases.Customer;
import com.sales.market.model.purchases.CustomerDiscount;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.CustomerDiscountRepository;
import com.sales.market.repository.purchases.CustomerRepository;
import com.sales.market.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService{
    private CustomerRepository customerRepository;

    @Override
    protected GenericRepository<Customer> getRepository() {
        return customerRepository;
    }
}
