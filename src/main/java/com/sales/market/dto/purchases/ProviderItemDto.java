package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.dto.ItemDto;
import com.sales.market.model.Item;
import com.sales.market.model.purchases.MeasureUnit;
import com.sales.market.model.purchases.Provider;
import com.sales.market.model.purchases.ProviderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
public class ProviderItemDto extends DtoBase<ProviderItem> {

    private ProviderDto provider;

    private ItemDto item;

    private String providerItemCode;

    private String providerCode;

    private String itemCode;

    private MeasureUnit measureUnit;

    private Double price;


}