package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.PurchaseOrderDto;
import com.sales.market.model.ModelBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "orderNumber")})
public class PurchaseOrder extends ModelBase<PurchaseOrderDto> {

    private String orderNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderState state;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderReceivedType receivedType;

    private String providerCode;

    // es igual a descripcion o comentarios
    private String gloss;

    @Temporal(TemporalType.DATE)
    private Date receptionDate;

    @Column(precision = 16, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Transient
    private PurchaseOrderDetail defaultDetail = new PurchaseOrderDetail();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Provider provider;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    @OrderBy("detailNumber asc")
    private List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetail>(0);

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PurchaseOrderPaymentStatus paymentStatus;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PurchaseOrderState getState() {
        return state;
    }

    public void setState(PurchaseOrderState state) {
        this.state = state;
    }

    public PurchaseOrderReceivedType getReceivedType() {
        return receivedType;
    }

    public void setReceivedType(PurchaseOrderReceivedType receivedType) {
        this.receivedType = receivedType;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getGloss() {
        return gloss;
    }

    public void setGloss(String gloss) {
        this.gloss = gloss;
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(Date receptionDate) {
        this.receptionDate = receptionDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PurchaseOrderDetail getDefaultDetail() {
        return defaultDetail;
    }

    public void setDefaultDetail(PurchaseOrderDetail defaultDetail) {
        this.defaultDetail = defaultDetail;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<PurchaseOrderDetail> getPurchaseOrderDetailList() {
        return purchaseOrderDetailList;
    }

    public void setPurchaseOrderDetailList(List<PurchaseOrderDetail> purchaseOrderDetailList) {
        this.purchaseOrderDetailList = purchaseOrderDetailList;
    }

    public PurchaseOrderPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PurchaseOrderPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

}
