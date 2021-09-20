package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.MeasureUnitDto;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class MeasureUnit extends ModelBase<MeasureUnitDto> {

    private String measureUnitCode;
    private String name;
    private String description;

}
