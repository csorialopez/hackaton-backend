/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market;

import com.sales.market.model.*;
import com.sales.market.model.purchases.MeasureUnit;
import com.sales.market.model.purchases.Provider;
import com.sales.market.model.purchases.ProviderItem;
import com.sales.market.repository.BuyRepository;
import com.sales.market.repository.EmployeeRepository;
import com.sales.market.service.*;
import com.sales.market.service.purchases.MeasureUnitService;
import com.sales.market.service.purchases.ProviderItemService;
import com.sales.market.service.purchases.ProviderService;
import io.micrometer.core.instrument.util.IOUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final BuyRepository buyRepository;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ItemService itemService;
    private final ItemInstanceService itemInstanceService;
    private final ProviderService providerService;
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final MeasureUnitService measureUnitService;
    private final ProviderItemService providerItemService;

    SubCategory beverageSubCat = null;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("evento de spring");
        /*   duplicacion de codigo
        Buy buy = new Buy();
        buy.setValue(BigDecimal.TEN);
        buyRespository.save(buy);
        Buy buy2 = new Buy();
        buy2.setValue(BigDecimal.ONE);
        buyRespository.save(buy);*/

        persistBuy(BigDecimal.TEN);
        persistBuy(BigDecimal.ONE);
        persistCategoriesAndSubCategories();
        Item maltinItem = persistItems(beverageSubCat);
        persistItemInstances(maltinItem);
        initializeRoles();
        initializeEmployees();
        persistMeasureUnit();
        createMeasureUnit();
    }

    private void persistMeasureUnit() {
        MeasureUnit measureUnit = new MeasureUnit();
        measureUnit.setName("Unidad");
        measureUnit.setDescription("Unidad de medida");
        measureUnit.setMeasureUnitCode("code1");
        measureUnitService.save(measureUnit);
    }

    private void initializeRoles() {
        createRole(RoleType.ADMIN.getId(), RoleType.ADMIN.getType());
        createRole(RoleType.GENERAL.getId(), RoleType.GENERAL.getType());
        createRole(RoleType.SUPERVISOR.getId(), RoleType.SUPERVISOR.getType());
    }

    private Role createRole(long id, String roleName) {
        Role role = new Role();
        role.setId(id);
        role.setName(roleName);
        roleService.save(role);
        return role;
    }

    private void initializeEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            createEmployee("Edson", "Terceros", "edsonariel@gmail.com", false);
            createEmployee("Ariel", "Terceros", "ariel@gmail.com", false);
            createEmployee("System", "", "edson@gmail.com", true);
        }
    }

    private void createEmployee(String firstName, String lastName, String email, boolean system) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employeeRepository.save(employee);
        createUser(email, employee, system);
    }

    private void createUser(String email, Employee employee, boolean system) {
        User user = new User();
        Role role = new Role();
        HashSet<Role> roles = new HashSet<>();

        user.setEmail(email);
        user.setEnabled(true);
        user.setSystem(system);
        user.setPassword("$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.");
        user.setEmployee(employee);

        role.setId(1L);
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
    }


    private void persistItemInstances(Item maltinItem) {
        ItemInstance maltinItem1 = createItem(maltinItem, "SKU-77721106006158", 5D);
        ItemInstance maltinItem2 = createItem(maltinItem, "SKU-77721106006159", 5D);
        ItemInstance maltinItem3 = createItem(maltinItem, "SKU-77721106006160", 5D);
        ItemInstance maltinItem4 = createItem(maltinItem, "SKU-77721106006161", 5D);
        itemInstanceService.save(maltinItem1);
        itemInstanceService.save(maltinItem2);
        itemInstanceService.save(maltinItem3);
        itemInstanceService.save(maltinItem4);
    }

    private ItemInstance createItem(Item maltinItem, String sku, double price) {
        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setItem(maltinItem);
        itemInstance.setFeatured(true);
        itemInstance.setPrice(price);
        itemInstance.setIdentifier(sku);
        return itemInstance;
    }

    private Item persistItems(SubCategory subCategory) {
        Item item = new Item();
        item.setCode("B-MALTIN");
        item.setName("MALTIN");
        item.setSubCategory(subCategory);
        /*try {
            item.setImage(ImageUtils.inputStreamToByteArray(getResourceAsStream("/images/maltin.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return itemService.save(item);
    }

    private String getResourceAsString(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private InputStream getResourceAsStream(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return inputStream;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void persistCategoriesAndSubCategories() {
        Category category = persistCategory();
        Category category1=persistCategory_pro();
        persistSubCategory("SUBCAT1-NAME", "SUBCAT1-CODE", category);
        persistSubCategory("SUB_LIM","SUB_LIM123",category1);
        beverageSubCat = persistSubCategory("BEVERAGE", "BEVERAGE-CODE", category);
    }

    private Category persistCategory() {
        Category category = new Category();
        category.setName("CAT1-NAME");
        category.setCode("CAT1-CODE");
        return categoryService.save(category);
    }

    private SubCategory persistSubCategory(String name, String code, Category category) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCode(code);
        subCategory.setCategory(category);
        return subCategoryService.save(subCategory);
    }

    private void persistBuy(BigDecimal value) {
        Buy buy = new Buy();
        buy.setValue(value);
        buyRepository.save(buy);
    }
    private Category persistCategory_pro() {
        Category category = new Category();
        category.setName("LIMPIEZA");
        category.setCode("LIMP-CODE");
        return categoryService.save(category);
    }
    private Provider provider(String code,String name){
        Provider provider=new Provider();
        provider.setCode(code);
        provider.setName(name);
        return  provider;
    }

    public MeasureUnit measureUnit(String name,String measureUnitCode){
        MeasureUnit measureUnit = new MeasureUnit();
        measureUnit.setName(name);
        measureUnit.setMeasureUnitCode(measureUnitCode);
        measureUnitService.save(measureUnit);
        return measureUnit;
    }
    private void createMeasureUnit(){
        Item item = persistItems(beverageSubCat);
        MeasureUnit measureUnit = measureUnit("unidad","U");
        measureUnitService.save(measureUnit);
        Provider provider=provider("P_1","Proveedor Escoba");
        Provider provider1=provider("P_2","Proveedor Escoba");
        Provider provider2=provider("P_3","Proveedor Escoba");
        providerService.save(provider);
        providerService.save(provider1);
        providerService.save(provider2);
        ProviderItem providerItem=providerItem(item,provider,new BigDecimal(20),measureUnit,"Prov Escoba");
        ProviderItem providerItem1=providerItem(item,provider1,new BigDecimal(10),measureUnit,"Prov Escoba_01");
        providerItemService.save(providerItem);
        providerItemService.save(providerItem1);
    }
    private ProviderItem providerItem(Item item,Provider provider,BigDecimal precio,MeasureUnit measureUnit,String codeProviderItem){
        ProviderItem providerItem =  new ProviderItem();
        providerItem.setItem(item);
        providerItem.setProviderItemCode(codeProviderItem);
        providerItem.setPrice(precio);
        providerItem.setMeasureUnit(measureUnit);
        providerItem.setProvider(provider);
      return providerItem;
    }
}
