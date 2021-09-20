package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.ProviderDto;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Provider extends ModelBase<ProviderDto> {
    private String name;

    private String code;

    @OneToMany(mappedBy = "provider")
    private List<ProviderItem> providerItemList;
}
