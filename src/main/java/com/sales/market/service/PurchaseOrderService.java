package com.sales.market.service;

import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.vo.PurchaseOrderPaymentVo;

import java.math.BigDecimal;

public interface PurchaseOrderService extends GenericService<PurchaseOrder> {

    PurchaseOrder updatePurchaseOrderStatus(PurchaseOrderPayment purchaseOrderPayment);

    public boolean verifyPurchaseOrderPaymentKindIsLiquidation (BigDecimal payAmount, Long purchaseOrderId);
}
