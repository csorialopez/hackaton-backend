package com.sales.market.service.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.purchases.*;
import com.sales.market.service.ItemServiceImpl;
import com.sales.market.service.PurchaseOrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseOrderPaymentServiceImplementationTest {

    @Autowired
    PurchaseOrderPaymentServiceImpl purchaseOrderPaymentService;

    @Autowired
    PurchaseOrderServiceImpl purchaseOrderService;

    @Autowired
    PurchaseOrderDetailServiceImpl purchaseOrderDetailService;

    @Autowired
    MeasureUnitServiceImpl measureUnitService;

    @Autowired
    ItemServiceImpl itemService;

    @Test
    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsPartial () {
        PurchaseOrder purchaseOrder = createPurchaseOrder("TEST-ORDER-1");

        PurchaseOrderPayment purchaseOrderPayment = createPurchaseOrderPayment(
                new BigDecimal("100"),
                PurchaseOrderPaymentKind.ADVANCE_PAYMENT,
                purchaseOrder);
        purchaseOrderPaymentService.registerOrderPayment(null);
        PurchaseOrder purchaseOrderUpdated = purchaseOrderService.findById(purchaseOrderPayment.getPurchaseOrder().getId());
        assertEquals(purchaseOrderUpdated.getBalanceAmount(), new BigDecimal("400"));
        assertEquals(purchaseOrderUpdated.getPaymentStatus(), PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);
        assertEquals(purchaseOrderUpdated.getState(), PurchaseOrderState.FIN);
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

        assertEquals(purchaseOrderUpdated.getBalanceAmount(), BigDecimal.ZERO);
        assertEquals(purchaseOrderUpdated.getPaymentStatus(), PurchaseOrderPaymentStatus.FULLY_PAID);
        assertEquals(purchaseOrderUpdated.getState(), PurchaseOrderState.LIQ);
    }

    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsTotalAndPayAmountIsNotEnoughShouldFail () {
        //todo implementar cuando se tenga el exceptionhandler
    }

    public PurchaseOrder createPurchaseOrder (String orderNumber) {
        Provider provider = new Provider();
        provider.setCode("TEST_PROVIDER-CODE");
        provider.setName("TEST PROVIDER NAME");

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(orderNumber);
        purchaseOrder.setDate(new Date());
        purchaseOrder.setState(PurchaseOrderState.FIN);
        purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.NO_PAYMENT);
        purchaseOrder.setTotalAmount(new BigDecimal("500"));
        purchaseOrder.setBalanceAmount(new BigDecimal("500"));
        purchaseOrder.setProvider(provider);

        PurchaseOrderDetail detail = new PurchaseOrderDetail();

        MeasureUnit measureUnit = new MeasureUnit();
        measureUnit.setDescription("TEST unit description");
        measureUnit.setName("TEST UNIT");
        measureUnit.setMeasureUnitCode("U");
        measureUnitService.save(measureUnit);

        Item item = new Item();
        item.setCode("it");
        item.setName("item name");
        itemService.save(item);

//        detail.setPurchaseOrder(purchaseOrder);
        detail.setQuantity(new BigDecimal("10"));
        detail.setMeasureUnit(measureUnit);
        detail.setItem(item);
        detail.setItemCode("TEST-item-code");
        detail.setProviderItemCode("TEST-PROVIDER-item-code");
        purchaseOrderDetailService.save(detail);
        purchaseOrder.setPurchaseOrderDetailList(Arrays.asList(detail));
        purchaseOrder.setDefaultDetail(detail);
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