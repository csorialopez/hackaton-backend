package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.dto.ItemDto;
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
    private ItemDto item;
    private String providerItemCode;

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public MeasureUnitDto getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnitDto measureUnit) {
        this.measureUnit = measureUnit;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
}
