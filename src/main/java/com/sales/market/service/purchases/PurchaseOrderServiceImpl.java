package com.sales.market.service.purchases;

import com.sales.market.model.purchases.PurchaseOrder;
import com.sales.market.model.purchases.ActionRequired;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.PurchaseOrderRepository;
import com.sales.market.model.purchases.PurchaseOrderState;
import com.sales.market.service.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderServiceImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final ActionRequiredService actionRequiredService;
    private final PurchaseOrderDetailService purchaseOrderDetailService;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository, ActionRequiredService actionRequiredService, PurchaseOrderDetailService purchaseOrderDetailService) {
        this.repository = repository;
        this.actionRequiredService = actionRequiredService;
        this.purchaseOrderDetailService = purchaseOrderDetailService;
    }

    @Override
    protected GenericRepository<PurchaseOrder> getRepository() {
        return repository;
    }

    @Override
    public PurchaseOrder save( PurchaseOrder model){
        purchaseOrderDetailService.saveAll(model.getPurchaseOrderDetailList());
        model.setState(PurchaseOrderState.PEN);
        PurchaseOrder saveOrder = repository.save(model);
        return findById(saveOrder.getId());
    }

    @Override
    public PurchaseOrder editPurchaseOrder(Long id, String notes) {
        PurchaseOrder purchaseOrder = findById(id);
        purchaseOrder.setState(PurchaseOrderState.PEN);
        purchaseOrder = save(purchaseOrder);
        ActionRequired actionRequired = new ActionRequired(purchaseOrder, notes);
        actionRequiredService.save(actionRequired);
        return findById(purchaseOrder.getId());
    }
}
