package com.sales.market.model.purchases;


import com.sales.market.dto.purchases.CustomerDto;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer extends ModelBase<CustomerDto> {

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String email;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date firstPurchase;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastPurchase;

    //totalarticulosadquiridos
    private Integer totalPurchasedProducts;

    //totalimporteadquirido
    private BigDecimal totalPurchasedAmount;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerDiscount> discounts = new ArrayList<CustomerDiscount>(0);

}
