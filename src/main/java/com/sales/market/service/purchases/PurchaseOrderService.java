package com.sales.market.service.purchases;

import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.service.GenericService;

public interface PurchaseOrderService extends GenericService<PurchaseOrder> {

    PurchaseOrder editPurchaseOrder(Long id, String notes);
}
