package com.sales.market.service.purchases;

import com.sales.market.exception.purchases.GenericException;
import com.sales.market.model.Item;
import com.sales.market.model.purchases.*;
import com.sales.market.service.ItemServiceImpl;
import com.sales.market.vo.PurchaseOrderPaymentVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

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

    @Autowired
    ProviderServiceImpl providerService;

    @Test
    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsPartial () {
        PurchaseOrder purchaseOrder = createPurchaseOrder("TEST-ORDER-1");
        PurchaseOrderPaymentVo vo = createPurchaseOrderPaymentVo(purchaseOrder.getOrderNumber(), "100", purchaseOrder.getId());
        purchaseOrderPaymentService.registerOrderPayment(vo);
        PurchaseOrder purchaseOrderUpdated = purchaseOrderService.findById(vo.getPurchaseOrderId());
        assertTrue(purchaseOrderUpdated.getBalanceAmount().compareTo(new BigDecimal("400")) == 0);
        assertEquals(purchaseOrderUpdated.getPaymentStatus(), PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);
        assertEquals(PurchaseOrderState.FIN, purchaseOrderUpdated.getState());
    }

    @Test
    public void testRegisterOrderPaymentWhenPurchaseOrderPaymentKindIsTotal () {
        PurchaseOrder purchaseOrder = createPurchaseOrder("TEST-ORDER-2");
        PurchaseOrderPaymentVo vo = createPurchaseOrderPaymentVo(purchaseOrder.getOrderNumber(), "500", purchaseOrder.getId());
        purchaseOrderPaymentService.registerOrderPayment(vo);
        PurchaseOrder purchaseOrderUpdated = purchaseOrderService.findById(vo.getPurchaseOrderId());
        assertTrue(purchaseOrderUpdated.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0);
        assertEquals(purchaseOrderUpdated.getPaymentStatus(), PurchaseOrderPaymentStatus.FULLY_PAID);
        assertEquals(purchaseOrderUpdated.getState(), PurchaseOrderState.LIQ);
    }

    @Test
    public void testRegisterOrderPaymentWhenPayAmountIsGreaterThanBalanceShouldFailAndShowExceptionMessage () {
        PurchaseOrder purchaseOrder = createPurchaseOrder("TEST-ORDER-3");
        String payAmount = "501.00";
        PurchaseOrderPaymentVo vo = createPurchaseOrderPaymentVo(purchaseOrder.getOrderNumber(), payAmount, purchaseOrder.getId());
        try {
            purchaseOrderPaymentService.registerOrderPayment(vo);
        } catch (GenericException e) {
            assertEquals(
                    "The payment amount is greater thar the required. You paid "+ payAmount+" but the required amount is only 500.00" ,
                    e.getMessage()
            );
            assertEquals(HttpStatus.BAD_REQUEST, e.getHttpStatus());
        }
    }

    public PurchaseOrder createPurchaseOrder (String orderNumber) {
        Provider provider = createProvider();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setOrderNumber(orderNumber);
        purchaseOrder.setDate(new Date());
        purchaseOrder.setState(PurchaseOrderState.FIN);
        purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.NO_PAYMENT);
        purchaseOrder.setTotalAmount(new BigDecimal("500"));
        purchaseOrder.setBalanceAmount(new BigDecimal("500"));
        purchaseOrder.setProvider(provider);

        PurchaseOrderDetail detail = new PurchaseOrderDetail();

        MeasureUnit measureUnit = createMeasureUnit();

        Item item = createItem("test");

//        detail.setPurchaseOrder(purchaseOrder);
        detail.setQuantity(new BigDecimal("10"));
        detail.setMeasureUnit(measureUnit);
        detail.setItem(item);
        detail.setItemCode("TEST-item-code");
        detail.setProviderItemCode("TEST-PROVIDER-item-code");
        purchaseOrderDetailService.save(detail);
        purchaseOrder.setPurchaseOrderDetailList(Arrays.asList());
//        purchaseOrder.setDefaultDetail(detail);
        return purchaseOrderService.saveAndFlush(purchaseOrder);
    }

    public PurchaseOrderPaymentVo createPurchaseOrderPaymentVo (String orderNumberPurchase, String payAmount, Long purchaseOrderId) {
        PurchaseOrderPaymentVo vo = new PurchaseOrderPaymentVo();
        vo.setDescription(orderNumberPurchase+ " description");
        vo.setPayAmount(payAmount);
        vo.setPurchaseOrderId(purchaseOrderId);
        return vo;
    }

    public Provider createProvider () {
        Provider provider = new Provider();
        provider.setCode("TEST-PROVIDER-CODE");
        provider.setName("TEST PROVIDER NAME");
        return providerService.save(provider);
    }

    public Item createItem (String name) {
        Item item = new Item();
        item.setCode("ITEM-"+name.toUpperCase());
        item.setName("item " + name);
        return itemService.save(item);
    }

    public MeasureUnit createMeasureUnit () {
        MeasureUnit measureUnit = new MeasureUnit();
        measureUnit.setDescription("TEST unit description");
        measureUnit.setName("TEST UNIT");
        measureUnit.setMeasureUnitCode("U");
        return measureUnitService.save(measureUnit);
    }

}