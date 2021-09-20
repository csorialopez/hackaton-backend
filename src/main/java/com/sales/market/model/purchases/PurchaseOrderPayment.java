package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.PurchaseOrderPaymentDto;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class PurchaseOrderPayment extends ModelBase<PurchaseOrderPaymentDto> {

    private String description;

    private BigDecimal payAmount;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderPaymentKind purchaseOrderPaymentKind;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrder purchaseOrder;
}
