package com.sales.market.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseOrderPaymentVo {

    private String description;
    private String payAmount;
    private Long purchaseOrderId;

}
