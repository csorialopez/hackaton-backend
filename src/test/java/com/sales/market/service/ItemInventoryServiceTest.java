package com.sales.market.service;

import com.sales.market.model.Category;
import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;
import com.sales.market.model.SubCategory;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@SpringBootTest
class ItemInventoryServiceTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemInventoryService itemInventoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testGetItemsLowerBoundery() {

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

        assertEquals(itemInventoryService.getItemsLowerBoundery().size(), 1);
    }
}