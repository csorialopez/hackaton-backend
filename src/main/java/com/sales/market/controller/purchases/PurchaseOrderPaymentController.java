/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.controller.purchases;

import com.sales.market.dto.purchases.PurchaseOrderPaymentDto;
import com.sales.market.service.purchases.PurchaseOrderPaymentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchaseorderpayments")
public class PurchaseOrderPaymentController {
    private PurchaseOrderPaymentService service;

    public PurchaseOrderPaymentController(PurchaseOrderPaymentService service) {
        this.service = service;
    }

    public PurchaseOrderPaymentDto registerPayment () {
        return null;
    }

}