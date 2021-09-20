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

@Setter
@Getter
@Entity
public class ProviderItem extends ModelBase<ProviderItemDto> {

    @ManyToOne
//    @JoinColumn(name = "id_provider",nullable = false)
    private Provider provider;

    @ManyToOne
//    @JoinColumn(name = "id_item",nullable = false)
    private Item item;

    private String providerItemCode;

    @OneToOne(targetEntity = MeasureUnit.class)
    private MeasureUnit measureUnit;

    private Double price;

}
