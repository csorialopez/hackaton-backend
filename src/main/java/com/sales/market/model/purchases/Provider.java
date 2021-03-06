package com.sales.market.model.purchases;

import com.sales.market.dto.purchases.ProviderDto;
import com.sales.market.model.ModelBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;



@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UK_PROVIDER_NAME", columnNames = {"name"}),
        @UniqueConstraint(name = "UK_PROVIDER_CODE", columnNames = {"code"})})
public class Provider extends ModelBase<ProviderDto> {
    private String name;

    private String code;

    @OneToMany(mappedBy = "provider")
    private List<ProviderItem> providerItemList;

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

    public List<ProviderItem> getProviderItemList() {
        return providerItemList;
    }

    public void setProviderItemList(List<ProviderItem> providerItemList) {
        this.providerItemList = providerItemList;
    }
}
