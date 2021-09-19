package com.sales.market.model.purchases;

import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class PurchaseOrder extends ModelBase {

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

}
