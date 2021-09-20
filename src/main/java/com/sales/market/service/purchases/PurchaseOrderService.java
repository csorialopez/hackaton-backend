package com.sales.market.service.purchases;

import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.model.purchases.PurchaseOrderPaymentKind;
import com.sales.market.model.purchases.PurchaseOrderPaymentStatus;
import com.sales.market.service.GenericService;

import java.math.BigDecimal;

public interface PurchaseOrderService extends GenericService<PurchaseOrder> {

    PurchaseOrder editPurchaseOrder(Long id, String notes);
    void updatePayment(Long id, PurchaseOrderPaymentStatus paymentKind, BigDecimal payAmount);

    PurchaseOrder updatePurchaseOrderStatus(PurchaseOrderPayment purchaseOrderPayment);
    public boolean verifyPurchaseOrderPaymentKindIsLiquidation (BigDecimal payAmount, Long purchaseOrderId);

}
