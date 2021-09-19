package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CustomerDto extends DtoBase<Customer> {
    private String number;
    private Date firstPurchase;
    private Date lastPurchase;
    private Integer totalPurchasedProducts;
    private BigDecimal totalPurchasedAmount;

}
