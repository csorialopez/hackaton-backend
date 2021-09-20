package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.CustomerDiscount;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerDiscountDto extends DtoBase<CustomerDiscount>{
    private String nameCustomerDiscountRule;
    private String numberCustomer;
    private String discountCode;
}
