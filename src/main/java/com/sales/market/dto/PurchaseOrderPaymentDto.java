/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.dto;

import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.model.purchases.PurchaseOrderPaymentKind;

import java.math.BigDecimal;

public class PurchaseOrderPaymentDto extends DtoBase<PurchaseOrderPayment> {

    private String description;
    private BigDecimal payAmount;
    private PurchaseOrderPaymentKind purchaseOrderPaymentKind;
    private PurchaseOrder purchaseOrder;
}
