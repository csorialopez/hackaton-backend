/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.controller.purchases;

import com.sales.market.controller.GenericController;
import com.sales.market.dto.purchases.PurchaseOrderPaymentDto;
import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.service.GenericService;
import com.sales.market.service.purchases.PurchaseOrderPaymentService;
import com.sales.market.vo.PurchaseOrderPaymentVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchaseorderpayments")
public class PurchaseOrderPaymentController extends GenericController<PurchaseOrderPayment, PurchaseOrderPaymentDto> {
    private PurchaseOrderPaymentService service;

    public PurchaseOrderPaymentController(PurchaseOrderPaymentService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }

    @PostMapping
    public PurchaseOrderPaymentDto registerPayment (@RequestBody PurchaseOrderPaymentVo purchaseOrderPaymentVo) {
        PurchaseOrderPayment purchaseOrderPayment = service.registerOrderPayment(purchaseOrderPaymentVo);
        return toDto(purchaseOrderPayment);
    }

}