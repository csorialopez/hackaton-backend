/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model.purchases;

import javax.persistence.*;

import com.sales.market.model.ModelBase;
import com.sales.market.dto.purchases.ActionRequiredDto;
import com.sales.market.model.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ActionRequired extends ModelBase<ActionRequiredDto> {

    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseOrder purchaseOrder;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public ActionRequired(){}
    public ActionRequired(PurchaseOrder purchaseOrder, String notes) {
        this.purchaseOrder = purchaseOrder;
        this.notes = notes;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
