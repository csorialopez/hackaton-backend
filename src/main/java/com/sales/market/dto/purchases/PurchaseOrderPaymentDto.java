/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.model.purchases.PurchaseOrderPaymentKind;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseOrderPaymentDto extends DtoBase<PurchaseOrderPayment> {

    private String description;
    private BigDecimal payAmount;
    private PurchaseOrderPaymentKind purchaseOrderPaymentKind;
    private PurchaseOrder purchaseOrder;
}
