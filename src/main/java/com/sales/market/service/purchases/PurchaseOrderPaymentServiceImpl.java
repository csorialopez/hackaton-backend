/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.service.purchases;

import com.sales.market.model.purchases.*;
import com.sales.market.repository.purchases.PurchaseOrderPaymentRepository;
import com.sales.market.repository.GenericRepository;
import com.sales.market.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseOrderPaymentServiceImpl extends GenericServiceImpl<PurchaseOrderPayment> implements PurchaseOrderPaymentService {
    private final PurchaseOrderPaymentRepository repository;

    public PurchaseOrderPaymentServiceImpl(PurchaseOrderPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<PurchaseOrderPayment> getRepository() {
        return repository;
    }

    @Override
    public PurchaseOrderPayment registerOrderPayment(PurchaseOrderPayment purchaseOrderPayment) {
        updatePurchaseOrderStatus(purchaseOrderPayment);
        return repository.save(purchaseOrderPayment);
    }

    public PurchaseOrder updatePurchaseOrderStatus (PurchaseOrderPayment purchaseOrderPayment) {
        PurchaseOrder purchaseOrder = purchaseOrderPayment.getPurchaseOrder();
        if (purchaseOrderPayment.getPurchaseOrderPaymentKind() == PurchaseOrderPaymentKind.ADVANCE_PAYMENT) {
            purchaseOrder.setBalanceAmount(purchaseOrder.getBalanceAmount().subtract(purchaseOrderPayment.getPayAmount()));
            purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);
        } else if (purchaseOrderPayment.getPurchaseOrderPaymentKind() == PurchaseOrderPaymentKind.LIQUIDATION_PAYMENT) {
            purchaseOrder.setBalanceAmount(BigDecimal.ZERO);
            purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.FULLY_PAID);
            purchaseOrder.setState(PurchaseOrderState.LIQ);
        }
        return purchaseOrder;
    }
}
