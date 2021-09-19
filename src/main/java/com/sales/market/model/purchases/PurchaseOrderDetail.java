package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.PurchaseOrderDetailDto;
import com.sales.market.model.Item;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class PurchaseOrderDetail extends ModelBase<PurchaseOrderDetailDto> {
    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Column(precision = 16, scale = 6)
    private BigDecimal unitCost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MeasureUnit measureUnit;

    @Column(precision = 16, scale = 6)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @Column(nullable = false)
    private String itemCode;

    @Column(nullable = false)
    private String providerItemCode;

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
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

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getProviderItemCode() {
        return providerItemCode;
    }

    public void setProviderItemCode(String providerItemCode) {
        this.providerItemCode = providerItemCode;
    }
}
