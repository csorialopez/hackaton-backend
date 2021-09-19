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
public class MeasureUnitDto extends DtoBase<MeasureUnit> {

    private String measureUnitCode;

    private String name;

    private String description;
}
