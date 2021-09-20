package com.sales.market.service.purchases;

import com.sales.market.model.purchases.Customer;
import com.sales.market.model.purchases.CustomerDiscount;
import com.sales.market.model.purchases.CustomerDiscountRule;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.CustomerDiscountRuleRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.service.mail.EmailService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerDiscountRuleServiceImpl extends GenericServiceImpl<CustomerDiscountRule> implements CustomerDiscountRuleService{
    private CustomerDiscountRuleRepository repository;
    private CustomerService customerService;
    private CustomerDiscountService customerDiscountService;
    private EmailService emailService;

    @Override
    protected GenericRepository<CustomerDiscountRule> getRepository() {
        return repository;
    }

    @Override
    public CustomerDiscountRule save(CustomerDiscountRule model) {
        if (model.getAmount().compareTo(new BigDecimal("50")) == 1){
        }
        model =  super.save(model);
        List<Customer> customers = customerService.findAll();
        List<CustomerDiscount> discounts = new LinkedList<>();
        String randomCode;
        for (Customer customer: customers
             ) {
            randomCode = RandomStringUtils.randomAlphanumeric(10);
            CustomerDiscount customerDiscount = new CustomerDiscount();
            customerDiscount.setCustomer(customer);
            customerDiscount.setDiscountRule(model);
            customerDiscount.setDiscountCode(randomCode);
            discounts.add(customerDiscount);
        }
        model.setDiscounts(discounts);
        customerDiscountService.saveAll(discounts);
        sendEmails(emailService,discounts);
        return model;
    }

    @Async
    void sendEmails(EmailService emailService, List<CustomerDiscount> discounts) {
        discounts
                .forEach(discount -> emailService.sendSimpleMessage(
                                        discount.getCustomer().getEmail(),
                                        "hola",
                                        "Codigo descuento: "+discount.getDiscountCode()));
    }

}
