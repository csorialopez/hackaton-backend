package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.Provider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDto extends DtoBase<Provider> {

    private String name;

    private String code;

}