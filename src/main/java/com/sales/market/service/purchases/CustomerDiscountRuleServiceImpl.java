package com.sales.market.service.purchases;

import com.sales.market.exception.purchases.GenericException;
import com.sales.market.model.purchases.Customer;
import com.sales.market.model.purchases.CustomerDiscount;
import com.sales.market.model.purchases.CustomerDiscountRule;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.CustomerDiscountRuleRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.service.mail.EmailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerDiscountRuleServiceImpl extends GenericServiceImpl<CustomerDiscountRule> implements CustomerDiscountRuleService{
    private final CustomerDiscountRuleRepository repository;
    private final CustomerService customerService;
    private final CustomerDiscountService customerDiscountService;
    private final EmailService emailService;

    @Value("${limit.discount}")
    private String limitDiscount;

    @Override
    protected GenericRepository<CustomerDiscountRule> getRepository() {
        return repository;
    }

    @Override
    public CustomerDiscountRule save(CustomerDiscountRule model) {
        System.out.println(limitDiscount);
        if (model.getAmount().compareTo(new BigDecimal(limitDiscount)) == 1){
            throw new GenericException("El descuento no puede superar el valor de " + "50", HttpStatus.BAD_REQUEST);
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
