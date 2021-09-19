/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model.purchases;

import javax.persistence.*;

import com.sales.market.model.ModelBase;
import com.sales.market.dto.ActionRequiredDto;
import com.sales.market.model.User;

@Entity
public class ActionRequired extends ModelBase<ActionRequiredDto> {

    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseOrder purchaseOrder;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
}
