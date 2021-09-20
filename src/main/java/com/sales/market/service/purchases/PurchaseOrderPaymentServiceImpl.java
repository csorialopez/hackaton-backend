/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.service.purchases;

import com.sales.market.model.purchases.*;
import com.sales.market.repository.purchases.PurchaseOrderPaymentRepository;
import com.sales.market.repository.GenericRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.vo.PurchaseOrderPaymentVo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseOrderPaymentServiceImpl extends GenericServiceImpl<PurchaseOrderPayment> implements PurchaseOrderPaymentService {
    private final PurchaseOrderPaymentRepository repository;
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderPaymentServiceImpl(PurchaseOrderPaymentRepository repository, PurchaseOrderService purchaseOrderService) {
        this.repository = repository;
        this.purchaseOrderService = purchaseOrderService;
    }

    @Override
    protected GenericRepository<PurchaseOrderPayment> getRepository() {
        return repository;
    }

    @Override
    public PurchaseOrderPayment registerOrderPayment(PurchaseOrderPaymentVo purchaseOrderPaymentvo) {
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(purchaseOrderPaymentvo.getPurchaseOrderId());
        PurchaseOrderPayment purchaseOrderPayment = new PurchaseOrderPayment();
        purchaseOrderPayment.setPayAmount(new BigDecimal(purchaseOrderPaymentvo.getPayAmount()));
        purchaseOrderPayment.setDescription(purchaseOrderPaymentvo.getDescription());
        if (purchaseOrderService.verifyPurchaseOrderPaymentKindIsLiquidation(new BigDecimal(purchaseOrderPaymentvo.getPayAmount()), purchaseOrderPaymentvo.getPurchaseOrderId())) {
            purchaseOrderPayment.setPurchaseOrderPaymentKind(PurchaseOrderPaymentKind.LIQUIDATION_PAYMENT);
        } else {
            purchaseOrderPayment.setPurchaseOrderPaymentKind(PurchaseOrderPaymentKind.ADVANCE_PAYMENT);
        }
        purchaseOrderPayment.setPurchaseOrder(purchaseOrder);
        purchaseOrderService.updatePurchaseOrderStatus(purchaseOrderPayment);
        return repository.save(purchaseOrderPayment);
    }

}
