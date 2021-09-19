package com.sales.market.service;

import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.model.purchases.PurchaseOrderPaymentKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.testng.Assert.*;

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

    }

    public PurchaseOrder createPurchaseOrder () {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber("TEST-ORDEN-1");
        purchaseOrder.setDate(new Date());
        return purchaseOrder;
    }
}