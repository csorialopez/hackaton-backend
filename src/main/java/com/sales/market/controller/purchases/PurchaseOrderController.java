package com.sales.market.controller.purchases;
import com.sales.market.controller.GenericController;
import com.sales.market.dto.purchases.PurchaseOrderDto;
import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.service.GenericService;
import com.sales.market.service.purchases.PurchaseOrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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

    @PreAuthorize("hasRole('SUPERVISOR')")
    @PostMapping(value = "/changeState/{orderId}")
    public PurchaseOrder changeStatePurchaseOrder(@RequestBody String notes, @PathVariable("orderId") @NotNull Long id){
        return purchaseOrderService.editPurchaseOrder(id, notes);
    }
}
