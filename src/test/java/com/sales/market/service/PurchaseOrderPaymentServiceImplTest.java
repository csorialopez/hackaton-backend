package com.sales.market.service;

import com.sales.market.model.purchases.*;
import com.sales.market.service.purchases.PurchaseOrderPaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class PurchaseOrderPaymentServiceImplTest {

    @Autowired
    PurchaseOrderPaymentServiceImpl purchaseOrderPaymentService;

    @Autowired
    PurchaseOrderServiceImpl purchaseOrderService;

    @Test
    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsPartial () {
        PurchaseOrder purchaseOrder = createPurchaseOrder("TEST-ORDER-1");

        PurchaseOrderPayment purchaseOrderPayment = createPurchaseOrderPayment(
                new BigDecimal("100"),
                PurchaseOrderPaymentKind.ADVANCE_PAYMENT,
                purchaseOrder);
        purchaseOrderPaymentService.registerOrderPayment(null);
        PurchaseOrder purchaseOrderUpdated = purchaseOrderService.findById(purchaseOrderPayment.getPurchaseOrder().getId());

        Assert.assertEquals(purchaseOrderUpdated.getBalanceAmount(), new BigDecimal("400"));
        Assert.assertEquals(purchaseOrderUpdated.getPaymentStatus(), PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);
        Assert.assertEquals(purchaseOrderUpdated.getState(), PurchaseOrderState.FIN);
    }

    @Test
    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsTotal () {
        PurchaseOrder purchaseOrder = createPurchaseOrder("TEST-ORDER-2");

        PurchaseOrderPayment purchaseOrderPayment = createPurchaseOrderPayment(
                new BigDecimal("500"),
                PurchaseOrderPaymentKind.LIQUIDATION_PAYMENT,
                purchaseOrder);
        purchaseOrderPaymentService.registerOrderPayment(null);
        PurchaseOrder purchaseOrderUpdated = purchaseOrderService.findById(purchaseOrderPayment.getPurchaseOrder().getId());

        Assert.assertEquals(purchaseOrderUpdated.getBalanceAmount(), BigDecimal.ZERO);
        Assert.assertEquals(purchaseOrderUpdated.getPaymentStatus(), PurchaseOrderPaymentStatus.FULLY_PAID);
        Assert.assertEquals(purchaseOrderUpdated.getState(), PurchaseOrderState.LIQ);
    }

    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsTotalAndPayAmountIsNotEnoughShouldFail () {
        //todo implementar cuando se tenga el exceptionhandler
    }

    public PurchaseOrder createPurchaseOrder (String orderNumber) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(orderNumber);
        purchaseOrder.setDate(new Date());
        purchaseOrder.setState(PurchaseOrderState.FIN);
        purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.NO_PAYMENT);
        purchaseOrder.setTotalAmount(new BigDecimal("500"));
        purchaseOrder.setBalanceAmount(new BigDecimal("500"));
        return purchaseOrderService.save(purchaseOrder);
    }

    public PurchaseOrderPayment createPurchaseOrderPayment (BigDecimal payAmount, PurchaseOrderPaymentKind purchaseOrderPaymentKind, PurchaseOrder purchaseOrder) {
        PurchaseOrderPayment purchaseOrderPayment = new PurchaseOrderPayment();
        purchaseOrderPayment.setDescription("TEST descripcion para orden de compra con monto " + payAmount + " y tipo de orden " + purchaseOrderPaymentKind.toString());
        purchaseOrderPayment.setPayAmount(payAmount);
        purchaseOrderPayment.setPurchaseOrderPaymentKind(purchaseOrderPaymentKind);
        purchaseOrderPayment.setPurchaseOrder(purchaseOrder);
        return purchaseOrderPayment;
    }

}