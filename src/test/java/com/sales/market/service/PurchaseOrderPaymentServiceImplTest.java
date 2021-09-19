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
    private PurchaseOrderPaymentServiceImpl purchaseOrderPaymentService;

    @Test
    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsPartial () {
        PurchaseOrder purchaseOrder = createPurchaseOrder();

        PurchaseOrderPayment purchaseOrderPayment = new PurchaseOrderPayment();
        purchaseOrderPayment.setDescription("TEST descripcion para orden de compra");
        purchaseOrderPayment.setPayAmount(new BigDecimal("100"));
        purchaseOrderPayment.setPurchaseOrderPaymentKind(PurchaseOrderPaymentKind.ADVANCE_PAYMENT);
        purchaseOrderPayment.setPurchaseOrder(purchaseOrder);
        purchaseOrderPaymentService.registerOrderPayment(purchaseOrderPayment);
        Assert.assertEquals(purchaseOrder.getBalanceAmount(), new BigDecimal("400"));
        Assert.assertEquals(purchaseOrder.getPaymentStatus(), PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);
        Assert.assertEquals(purchaseOrder.getState(), PurchaseOrderState.FIN);
    }

    public PurchaseOrder createPurchaseOrder () {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber("TEST-ORDEN-1");
        purchaseOrder.setDate(new Date());
        purchaseOrder.setState(PurchaseOrderState.FIN);
        purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.NO_PAYMENT);
        purchaseOrder.setTotalAmount(new BigDecimal("500"));
        purchaseOrder.setBalanceAmount(new BigDecimal("500"));
        return purchaseOrder;
    }

}