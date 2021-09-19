/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.service;

import com.sales.market.model.purchases.PurchaseOrderPayment;

public interface PurchaseOrderPaymentService extends GenericService<PurchaseOrderPayment> {

    PurchaseOrderPayment registerOrderPayment(PurchaseOrderPayment purchaseOrderPayment);
}