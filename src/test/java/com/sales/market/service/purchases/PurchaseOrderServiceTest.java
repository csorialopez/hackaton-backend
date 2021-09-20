package com.sales.market.service.purchases;

import com.sales.market.model.Category;
import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.SubCategory;
import com.sales.market.model.purchases.*;
import com.sales.market.service.CategoryService;
import com.sales.market.service.ItemInventoryService;
import com.sales.market.service.ItemService;
import com.sales.market.service.SubCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.testng.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PurchaseOrderServiceTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemInventoryService itemInventoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    public PurchaseOrderService purchaseOrderService;
    @Autowired
    public ProviderService providerService;
    @Autowired
    public ProviderItemService providerItemService;
    @Autowired
    public MeasureUnitService measureUnitService;
    @Test
    void solicitarOrden() {
        Category category = new Category();
        category.setName("CAT1-NAME");
        category.setCode("CAT1-CODE");
        categoryService.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("BEVERAGE");
        subCategory.setCode("BEVERAGE-CODE");
        subCategory.setCategory(category);
        subCategoryService.save(subCategory);

        Item item = new Item();
        item.setCode("B-PEPSI");
        item.setName("PEPSI");
        item.setSubCategory(subCategory);
        itemService.save(item);

        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setItem(item);
        itemInventory.setStockQuantity(new BigDecimal(6));
        itemInventory.setLowerBoundThreshold(new BigDecimal(5));
        itemInventory.setUpperBoundThreshold(new BigDecimal(10));
        itemInventoryService.save(itemInventory);

        Item item1 = new Item();
        item1.setCode("C-7UP");
        item1.setName("7UP");
        itemService.save(item1);

        ItemInventory itemInventory1 = new ItemInventory();
        itemInventory1.setItem(item);
        itemInventory1.setStockQuantity(new BigDecimal(5));
        itemInventory1.setLowerBoundThreshold(new BigDecimal(5));
        itemInventory1.setUpperBoundThreshold(new BigDecimal(10));
        itemInventoryService.save(itemInventory1);

        Item item2 = new Item();
        item2.setCode("B-COKE");
        item2.setName("COKE");
        itemService.save(item2);

        ItemInventory itemInventory2 = new ItemInventory();
        itemInventory2.setItem(item);
        itemInventory2.setStockQuantity(new BigDecimal(8));
        itemInventory2.setLowerBoundThreshold(new BigDecimal(5));
        itemInventory2.setUpperBoundThreshold(new BigDecimal(10));
        itemInventoryService.save(itemInventory2);

        Provider provider1 = new Provider();
        provider1.setCode("PEPSI");
        provider1.setName("Proveedor pepsi");
        providerService.save(provider1);

        Provider provider2 = new Provider();
        provider1.setCode("COCA COLA");
        provider1.setName("Proveedor cocacola");
        providerService.save(provider2);

        MeasureUnit measureUnit = new MeasureUnit();
        measureUnit.setName("unidad");
        measureUnit.setMeasureUnitCode("U");
        measureUnitService.save(measureUnit);

        ProviderItem providerItem =  new ProviderItem();
        providerItem.setItem(item);
        providerItem.setProviderItemCode("gaseosa pequeña");
        providerItem.setPrice(new BigDecimal((7)));
        providerItem.setMeasureUnit(measureUnit);
        providerItem.setProvider(provider1);
        providerItemService.save(providerItem);

        ProviderItem providerItem1 =  new ProviderItem();
        providerItem1.setItem(item1);
        providerItem1.setProviderItemCode("gaseosa pequeña");
        providerItem1.setPrice(new BigDecimal((5)));
        providerItem1.setMeasureUnit(measureUnit);
        providerItem1.setProvider(provider1);
        providerItemService.save(providerItem1);

        ProviderItem providerItem2 =  new ProviderItem();
        providerItem2.setItem(item2);
        providerItem2.setProviderItemCode("gaseosa grande");
        providerItem2.setPrice(new BigDecimal((10)));
        providerItem2.setMeasureUnit(measureUnit);
        providerItem2.setProvider(provider2);
        providerItemService.save(providerItem2);

        List<PurchaseOrder> listaPurchases = purchaseOrderService.solicitarOrden(itemInventoryService.getItemsLowerBoundery());
        assertEquals(listaPurchases.size(), 1);


    }

}