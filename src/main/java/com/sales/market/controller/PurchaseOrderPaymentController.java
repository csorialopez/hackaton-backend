/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.controller;

import com.sales.market.dto.PurchaseOrderPaymentDto;
import com.sales.market.model.purchases.PurchaseOrderPayment;
import com.sales.market.service.PurchaseOrderPaymentService;
import com.sales.market.service.GenericService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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