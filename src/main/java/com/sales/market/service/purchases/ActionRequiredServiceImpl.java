package com.sales.market.service.purchases;

import com.sales.market.model.purchases.ActionRequired;
import com.sales.market.repository.purchases.ActionRequiredRepository;
import com.sales.market.repository.GenericRepository;
import com.sales.market.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service("ActionRequiredService")
public class ActionRequiredServiceImpl extends GenericServiceImpl<ActionRequired> implements ActionRequiredService {
    private ActionRequiredRepository repository;
    public ActionRequiredServiceImpl(ActionRequiredRepository repository) { this.repository = repository;}

    @Override
    protected GenericRepository<ActionRequired> getRepository(){ return repository;}
}
