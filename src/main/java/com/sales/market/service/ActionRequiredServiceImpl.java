package com.sales.market.service;

import com.sales.market.model.purchases.ActionRequired;
import com.sales.market.repository.ActionRequiredRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service("ActionRequiredService")
public class ActionRequiredServiceImpl extends GenericServiceImpl<ActionRequired> implements ActionRequiredService {
    private ActionRequiredRepository repository;
    public ActionRequiredServiceImpl(ActionRequiredRepository repository) { this.repository = repository;}

    @Override
    protected GenericRepository<ActionRequired> getRepository(){ return repository;}
}
