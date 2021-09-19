package com.sales.market.service;

import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.ActionRequired;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.PurchaseOrderRepository;
import com.sales.market.model.purchases.PurchaseOrderState;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final ActionRequiredService actionRequiredService;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository, ActionRequiredService actionRequiredService) {
        this.repository = repository;
        this.actionRequiredService = actionRequiredService;
    }

    @Override
    protected GenericRepository<PurchaseOrder> getRepository() {
        return repository;
    }

    @Override
    public PurchaseOrder save( PurchaseOrder model){
        // purchanseOrderDetailsService.saveAll(model.purchaseOrderDetailList());
        model.setState(PurchaseOrderState.PEN);
        PurchaseOrder saveOrder = repository.save(model);
        ActionRequired actionRequired = new ActionRequired();
        actionRequired.setPurchaseOrder(saveOrder);
        actionRequired.setNotes("Action de creacion");
        actionRequiredService.save(actionRequired);
        return findById(saveOrder.getId());
    }
}
