/**
 * @author: Antony B. Maceda C.
 */

package com.sales.market.service;
import com.sales.market.model.PurchaseOrderPayment;
import com.sales.market.repositories.PurchaseOrderPaymentRepository;
import com.sales.market.repositories.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderPaymentServiceImpl extends GenericServiceImpl<PurchaseOrderPayment> implements PurchaseOrderPaymentService{
    private final PurchaseOrderPaymentRepository repository;

    public PurchaseOrderPaymentServiceImpl(PurchaseOrderPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<PurchaseOrderPayment> getRepository() {
        return repository;
    }
}
