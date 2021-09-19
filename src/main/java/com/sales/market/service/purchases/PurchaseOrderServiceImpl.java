package com.sales.market.service.purchases;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.purchases.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.PurchaseOrderRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.service.ItemInventoryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final ActionRequiredService actionRequiredService;
    private final PurchaseOrderDetailService purchaseOrderDetailService;
    private final ItemInventoryService itemInventoryService;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository, ActionRequiredService actionRequiredService,
                                    PurchaseOrderDetailService purchaseOrderDetailService, ItemInventoryService itemInventoryService) {
        this.repository = repository;
        this.actionRequiredService = actionRequiredService;
        this.purchaseOrderDetailService = purchaseOrderDetailService;
        this.itemInventoryService = itemInventoryService;
    }

    @Override
    protected GenericRepository<PurchaseOrder> getRepository() {
        return repository;
    }

    public PurchaseOrder solicitarOrden(List<ItemInventory> listaItems, String numberPurchaseOrder) {
        PurchaseOrder purchase = new PurchaseOrder();
        purchase.setPurchaseOrderDetailList(getOrderDetailsByItem(listaItems));
        purchase.setOrderNumber(numberPurchaseOrder);
        purchase.setDate(Date.from(Instant.now()));
        purchase.setState(PurchaseOrderState.PEN);
        purchase.setReceivedType(PurchaseOrderReceivedType.NR);
        purchase.setPaymentStatus(PurchaseOrderPaymentStatus.NO_PAYMENT);
        purchase.setTotalAmount(getTotalAmountDetail(listaItems));
        purchase.setBalanceAmount(BigDecimal.ZERO);
        return purchase;
    }

    private BigDecimal getTotalAmountDetail(List<ItemInventory> listaItems){
        List<BigDecimal> totalAmountDetail = getOrderDetailsByItem(listaItems).stream().map(detail->
                detail.getTotalAmount()).collect(Collectors.toList());
        BigDecimal sum = BigDecimal.ZERO;
        for(BigDecimal total: totalAmountDetail){
            sum.add(total);
        }
        return sum;
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

    @Override
    public void updatePayment(Long id, PurchaseOrderPaymentStatus paymentStatus, BigDecimal payAmount) {
        PurchaseOrder purchaseOrder = findById(id);
        purchaseOrder.setPaymentStatus(paymentStatus);
        if (paymentStatus.equals(PurchaseOrderPaymentStatus.FULLY_PAID)) {
            purchaseOrder.setState(PurchaseOrderState.LIQ);
            purchaseOrder.setBalanceAmount(BigDecimal.ZERO);
        } else {
            purchaseOrder.setBalanceAmount(purchaseOrder.getBalanceAmount().subtract(payAmount));
        }
        save(purchaseOrder);
    }

    private List<PurchaseOrderDetail> getOrderDetailsByItem(List<ItemInventory> itemsInventory){
        List<PurchaseOrderDetail> orderDetails = new ArrayList<>();
        for (ItemInventory item: itemsInventory ) {
            BigDecimal quantity = item.getUpperBoundThreshold().subtract(item.getStockQuantity());
            ProviderItem providerItem = new ProviderItem();//providerItemService.findByIdItem(item.getItem().getId());
            PurchaseOrderDetail orderDetail = new PurchaseOrderDetail();
            orderDetail.setItem(item.getItem());
            orderDetail.setItemCode(item.getItem().getCode());
            orderDetail.setProviderItemCode(providerItem.getProviderItemCode());
            orderDetail.setMeasureUnit(providerItem.getMeasureUnit());
            orderDetail.setTotalAmount(quantity.multiply(providerItem.getPrice()));
            orderDetails.add(orderDetail);
        }
        return purchaseOrderDetailService.saveAll(orderDetails);
    }
}
