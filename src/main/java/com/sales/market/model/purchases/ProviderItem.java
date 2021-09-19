/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model.purchases;

import com.sales.market.model.Item;

import java.math.BigDecimal;

public class ProviderItem {

    private Provider provider;
    //codigo con el que el proveedor conoce al item
    private String providerItemCode;

    //facilitara los queries
    private String providerCode;

    private Item item;

    //facilitara los queries
    private String itemCode;

    private MeasureUnit measureUnit;

    private BigDecimal price;

    public void setProvider(Provider provider) {
        this.provider = provider;
        this.providerCode = provider.getCode();
    }

    public void setItem(Item item) {
        this.item = item;
        this.itemCode = item.getCode();
    }

    public Provider getProvider() {
        return provider;
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

    public Item getItem() {
        return item;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
