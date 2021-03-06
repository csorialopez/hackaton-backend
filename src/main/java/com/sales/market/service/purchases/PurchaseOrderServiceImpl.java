package com.sales.market.service.purchases;

import com.sales.market.exception.purchases.GenericException;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.purchases.*;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.PurchaseOrderRepository;
import com.sales.market.service.GenericServiceImpl;
import com.sales.market.service.ItemInventoryService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl extends GenericServiceImpl<PurchaseOrder> implements PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final ActionRequiredService actionRequiredService;
    private final PurchaseOrderDetailService purchaseOrderDetailService;
    private final ItemInventoryService itemInventoryService;
    private final ProviderItemService providerItemService;

    public Set<Provider> providers = new HashSet<>();

    public PurchaseOrderServiceImpl(PurchaseOrderRepository repository, ActionRequiredService actionRequiredService,
                                    PurchaseOrderDetailService purchaseOrderDetailService, ItemInventoryService itemInventoryService,
                                    ProviderItemService providerItemService) {
        this.repository = repository;
        this.actionRequiredService = actionRequiredService;
        this.purchaseOrderDetailService = purchaseOrderDetailService;
        this.itemInventoryService = itemInventoryService;
        this.providerItemService = providerItemService;
    }

    @Override
    protected GenericRepository<PurchaseOrder> getRepository() {
        return repository;
    }

    @Override
    public List<PurchaseOrder> solicitarOrden(List<ItemInventory> listaItems) {
        List<PurchaseOrderDetail>orderDetails = getOrderDetailsByItem(listaItems);
        List<PurchaseOrder> purchaseOrders = new ArrayList<>();
        providers.forEach((provider -> {
            List<PurchaseOrderDetail> orderDetails1 = new ArrayList<>();
            orderDetails.forEach(purchaseOrderDetail -> {
                if (purchaseOrderDetail.getProvider() == provider){
                    orderDetails1.add(purchaseOrderDetail);
                }
            });
            purchaseOrders.add(createPurchaseOrder(orderDetails1, provider));

        }));
        return purchaseOrders;
    }

    private BigDecimal getTotalAmountDetail(List<PurchaseOrderDetail> listaItems){
        List<BigDecimal> totalAmountDetail = listaItems.stream().map(detail->
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

    @Override
    public PurchaseOrder updatePurchaseOrderStatus(PurchaseOrderPayment purchaseOrderPayment) {
        PurchaseOrder purchaseOrder = findById(purchaseOrderPayment.getPurchaseOrder().getId());
        if (purchaseOrderPayment.getPurchaseOrderPaymentKind() == PurchaseOrderPaymentKind.ADVANCE_PAYMENT) {
            purchaseOrder.setBalanceAmount(purchaseOrder.getBalanceAmount().subtract(purchaseOrderPayment.getPayAmount()));
            if (purchaseOrder.getPaymentStatus() == PurchaseOrderPaymentStatus.NO_PAYMENT) {
                purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.PARTIAL_PAYMENT);
            }
        } else if (purchaseOrderPayment.getPurchaseOrderPaymentKind() == PurchaseOrderPaymentKind.LIQUIDATION_PAYMENT) {
            purchaseOrder.setBalanceAmount(BigDecimal.ZERO);
            purchaseOrder.setPaymentStatus(PurchaseOrderPaymentStatus.FULLY_PAID);
            purchaseOrder.setState(PurchaseOrderState.LIQ);
        }
        return save(purchaseOrder);
    }

    @Override
    public boolean verifyPurchaseOrderPaymentKindIsLiquidation (BigDecimal payAmount, Long purchaseOrderId) {
        PurchaseOrder purchaseOrder = findById(purchaseOrderId);
        BigDecimal balanceUpdated = purchaseOrder.getBalanceAmount().subtract(payAmount);
        if (balanceUpdated.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        if (balanceUpdated.compareTo(BigDecimal.ZERO) == 1) {
            return false;
        }
        if (balanceUpdated.compareTo(BigDecimal.ZERO) == -1) {
            throw new GenericException("The payment amount is greater thar the required. You paid " + payAmount + " but the required amount is only " + purchaseOrder.getBalanceAmount(), HttpStatus.BAD_REQUEST);
        }
        return false;
    }

    private List<PurchaseOrderDetail> getOrderDetailsByItem(List<ItemInventory> itemsInventory){
        List<PurchaseOrderDetail> orderDetails = new ArrayList<>();
        for (ItemInventory item: itemsInventory ) {
            BigDecimal quantity = item.getUpperBoundThreshold().subtract(item.getStockQuantity());
            ProviderItem providerItem = providerItemService.getProviderItemsBy(item.getItem());
            PurchaseOrderDetail orderDetail = new PurchaseOrderDetail();
            orderDetail.setItem(item.getItem());
            orderDetail.setItemCode(item.getItem().getCode());
            orderDetail.setProviderItemCode(providerItem.getProviderItemCode());
            orderDetail.setMeasureUnit(providerItem.getMeasureUnit());
            orderDetail.setTotalAmount(quantity.multiply(providerItem.getPrice()));
            orderDetail.setProvider(providerItem.getProvider());
            orderDetail.setUnitCost(providerItem.getPrice());
            orderDetail.setQuantity(quantity);
            providers.add(providerItem.getProvider());
            orderDetails.add(orderDetail);
            item.setStockQuantity(item.getStockQuantity().add(quantity));
            itemInventoryService.saveAndFlush(item);
        }
        return orderDetails;
    }

    private PurchaseOrder createPurchaseOrder ( List<PurchaseOrderDetail> listDetails, Provider provider) {
        String generatedString = RandomStringUtils.randomAlphanumeric(12);
        PurchaseOrder purchase = new PurchaseOrder();
        purchase.setPurchaseOrderDetailList(listDetails);
        purchase.setOrderNumber(generatedString);
        purchase.setDate(Date.from(Instant.now()));
        purchase.setState(PurchaseOrderState.PEN);
        purchase.setReceivedType(PurchaseOrderReceivedType.NR);
        purchase.setPaymentStatus(PurchaseOrderPaymentStatus.NO_PAYMENT);
        purchase.setTotalAmount(getTotalAmountDetail(listDetails));
        purchase.setBalanceAmount(getTotalAmountDetail(listDetails));
        purchase.setProvider(provider);
        purchase.setProviderCode(provider.getCode());

        return save(purchase);
    }
}
