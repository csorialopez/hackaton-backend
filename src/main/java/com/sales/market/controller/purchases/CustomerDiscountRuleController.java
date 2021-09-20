/**
 * @author: Henry Zerda R.
 */

package com.sales.market.controller.purchases;

import com.sales.market.controller.GenericController;
import com.sales.market.dto.purchases.CustomerDiscountRuleDto;
import com.sales.market.model.purchases.CustomerDiscountRule;
import com.sales.market.service.GenericService;
import com.sales.market.service.purchases.CustomerDiscountRuleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customerdiscountrules")
@AllArgsConstructor
public class CustomerDiscountRuleController extends GenericController<CustomerDiscountRule, CustomerDiscountRuleDto> {
    private  CustomerDiscountRuleService service;


    @Override
    protected GenericService getService() {
        return service;
    }

}
