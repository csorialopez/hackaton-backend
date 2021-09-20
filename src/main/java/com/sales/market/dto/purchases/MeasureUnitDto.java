package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.MeasureUnit;
import lombok.Getter;
import lombok.Setter;


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
