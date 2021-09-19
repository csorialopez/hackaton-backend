/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.service.purchases;

import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.service.GenericService;

public interface PurchaseOrderPaymentService extends GenericService<PurchaseOrderPayment> {

    PurchaseOrderPayment registerOrderPayment(PurchaseOrderPayment purchaseOrderPayment);
}