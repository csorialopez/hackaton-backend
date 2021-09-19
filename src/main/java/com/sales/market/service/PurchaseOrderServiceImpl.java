package com.sales.market.service;

import com.sales.market.exception.purchases.GenericException;
import com.sales.market.model.purchases.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.PurchaseOrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseOrderServiceImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final ActionRequiredService actionRequiredService;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository, ActionRequiredService actionRequiredService) {
        this.repository = repository;
        this.actionRequiredService = actionRequiredService;
    }

    @Override
    protected GenericRepository<PurchaseOrder> getRepository() {
        return repository;
    }

    @Override
    public PurchaseOrder save( PurchaseOrder model){
        // purchanseOrderDetailsService.saveAll(model.purchaseOrderDetailList());
        model.setState(PurchaseOrderState.PEN);
        PurchaseOrder saveOrder = repository.save(model);
        ActionRequired actionRequired = new ActionRequired();
        actionRequired.setPurchaseOrder(saveOrder);
        actionRequired.setNotes("Action de creacion");
        actionRequiredService.save(actionRequired);
        return findById(saveOrder.getId());
    }

    @Override
    public PurchaseOrder updatePurchaseOrderStatus(PurchaseOrderPayment purchaseOrderPayment) {
        PurchaseOrder purchaseOrder = findById(purchaseOrderPayment.getPurchaseOrder().getId());
        if (purchaseOrderPayment.getPurchaseOrderPaymentKind() == PurchaseOrderPaymentKind.ADVANCE_PAYMENT) {
            purchaseOrder.setBalanceAmount(purchaseOrder.getBalanceAmount().subtract(purchaseOrderPayment.getPayAmount()));
            if (purchaseOrder.getPaymentStatus() == PurchaseOrderPaymentStatus.NO_PAYMENT) {
                purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);
            }
        } else if (purchaseOrderPayment.getPurchaseOrderPaymentKind() == PurchaseOrderPaymentKind.LIQUIDATION_PAYMENT) {
            purchaseOrder.setBalanceAmount(BigDecimal.ZERO);
            purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.FULLY_PAID);
            purchaseOrder.setState(PurchaseOrderState.LIQ);
        }
        return repository.save(purchaseOrder);
    }

    @Override
    public boolean verifyPurchaseOrderPaymentKindIsLiquidation (BigDecimal payAmount, Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = findById(purchaseOrderId);
        BigDecimal balanceUpdated = purchaseOrder.getBalanceAmount().subtract(payAmount);
        if (balanceUpdated.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        if (balanceUpdated.compareTo(BigDecimal.ZERO) == 1) {
            return false;
        }
        if (balanceUpdated.compareTo(BigDecimal.ZERO) == -1) {
            throw new GenericException("The payment amount is greater thar the required. You paid " + payAmount + " but the required amount is only " + purchaseOrder.getBalanceAmount(), HttpStatus.BAD_REQUEST);
        }
        return false;
    }
}
