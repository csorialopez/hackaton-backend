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

//@Getter
//@Setter
public class ProviderItemDto extends DtoBase<ProviderItem> {

    private ProviderDto provider;

    private ItemDto item;

    private String providerItemCode;

    private String providerCode;

    private String itemCode;

    private MeasureUnitDto measureUnit;

    private Double price;

    public ProviderDto getProvider() {
        return provider;
    }

    public void setProvider(ProviderDto provider) {
        this.provider = provider;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public String getProviderItemCode() {
        return providerItemCode;
    }

    public void setProviderItemCode(String providerItemCode) {
        this.providerItemCode = providerItemCode;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public MeasureUnitDto getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnitDto measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}