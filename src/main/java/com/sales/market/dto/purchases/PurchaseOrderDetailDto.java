package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.Item;
import com.sales.market.model.purchases.MeasureUnit;
import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.PurchaseOrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author arturo
 */
@Getter
@Setter
public class PurchaseOrderDetailDto extends DtoBase<PurchaseOrderDetail> {

    private String purchaseOrderNumber;
    private BigDecimal quantity;
    private BigDecimal unitCost;
    private MeasureUnitDto measureUnit;
    private BigDecimal totalAmount;
    private String itemName;
    private String itemCode;
    private String providerItemCode;
}
