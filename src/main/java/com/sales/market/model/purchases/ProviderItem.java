/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.ProviderItemDto;
import com.sales.market.model.Item;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
public class ProviderItem extends ModelBase<ProviderItemDto> {

    @ManyToOne(optional = true)
    private Provider provider;

    @ManyToOne(optional = true)
    private Item item;

    private String providerItemCode;

    private String providerCode;

    private String itemCode;

    @OneToOne(targetEntity = MeasureUnit.class)
    private MeasureUnit measureUnit;

    private Double price;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
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

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
