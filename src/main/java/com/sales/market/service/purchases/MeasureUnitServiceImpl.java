package com.sales.market.service.purchases;

import com.sales.market.model.purchases.MeasureUnit;
import com.sales.market.model.purchases.PurchaseOrderDetail;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.purchases.MeasureUnitRepository;
import com.sales.market.repository.purchases.PurchaseOrderDetailRepository;
import com.sales.market.service.GenericServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author arturo
 */
@Service
@AllArgsConstructor
public class MeasureUnitServiceImpl extends GenericServiceImpl<MeasureUnit> {
    private final MeasureUnitRepository repository;


    @Override
    protected GenericRepository<MeasureUnit> getRepository() {
        return repository;
    }
}
