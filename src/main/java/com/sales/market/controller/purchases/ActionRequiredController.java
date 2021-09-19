package com.sales.market.controller.purchases;

import com.sales.market.controller.GenericController;
import com.sales.market.dto.purchases.ActionRequiredDto;
import com.sales.market.model.purchases.ActionRequired;
import com.sales.market.service.purchases.ActionRequiredService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actionrequires")
public class ActionRequiredController extends GenericController<ActionRequired, ActionRequiredDto> {
    private ActionRequiredService service;

    public ActionRequiredController(ActionRequiredService service) {
        this.service = service;
    }

    @Override
    protected ActionRequiredService getService() {
        return service;
    }
}