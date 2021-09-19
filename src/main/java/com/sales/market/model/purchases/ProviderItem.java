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

@Getter
@Setter
@Entity
public class ProviderItem extends ModelBase<ProviderItemDto> {

    @ManyToOne(optional = false)
    private Provider provider;
    @ManyToOne(optional = false)
    private Item item;

    private String providerItemCode;

    private String providerCode;

    private String itemCode;

    @OneToOne(targetEntity = MeasureUnit.class)
    private MeasureUnit measureUnit;

    private Double price;

}
