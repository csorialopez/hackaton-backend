package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.dto.ItemDto;
import com.sales.market.model.purchases.ProviderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderItemDto extends DtoBase<ProviderItem> {

    private ProviderDto provider;

    private ItemDto item;

    private String providerItemCode;

    private MeasureUnitDto measureUnit;

    private Double price;


}