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
import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class ProviderItem extends ModelBase<ProviderItemDto> {

    @ManyToOne
    private Provider provider;

    @ManyToOne
    private Item item;

    private String providerItemCode;

    @OneToOne(targetEntity = MeasureUnit.class)
    private MeasureUnit measureUnit;

    private BigDecimal price;

}
