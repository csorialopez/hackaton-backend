package com.sales.market.dto.purchases;

import com.sales.market.dto.DtoBase;
import com.sales.market.model.purchases.Provider;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class ProviderDto extends DtoBase<Provider> {

    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}