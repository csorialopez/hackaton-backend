package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.CustomerDiscountRule;
import com.sales.market.model.purchases.DiscountRuleState;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CustomerDiscountRuleDto extends DtoBase<CustomerDiscountRule> {
    private String name;
    private DiscountRuleState discountRuleState;
    private Date activationDate;
    private Date expirationDate;
    private String notes;
    private BigDecimal amount;
    private List<CustomerDiscountDto> discounts = new ArrayList<>(0);
}
