package com.sales.market.model.purchases;


import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class CustomerDiscountRule extends ModelBase {
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountRuleState discountRuleState;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date activationDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date expirationDate;

    @Lob
    private String notes;

    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal amount;

    @OneToMany(mappedBy = "discountRule")
    private List<CustomerDiscount> discounts = new ArrayList<CustomerDiscount>(0);
}
