package com.sales.market.controller;
import com.sales.market.dto.PurchaseOrderDto;
import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.service.GenericService;
import com.sales.market.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchaseOrders")
public class PurchaseOrderController extends GenericController<PurchaseOrder, PurchaseOrderDto> {
    private PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @Override
    protected GenericService getService() {
        return purchaseOrderService;
    }
}
