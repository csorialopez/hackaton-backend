package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.CustomerDiscountDto;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class CustomerDiscount extends ModelBase<CustomerDiscountDto> {

    @ManyToOne
    private CustomerDiscountRule discountRule;

    @ManyToOne
    private Customer customer;

    private String discountCode;
}
