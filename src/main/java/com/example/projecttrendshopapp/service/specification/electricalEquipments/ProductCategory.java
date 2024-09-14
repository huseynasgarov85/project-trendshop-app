package com.example.projecttrendshopapp.service.specification.electricalEquipments;

import com.example.projecttrendshopapp.enums.ProductCategoryElectricalEquipments;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategory implements Specification {
    private final ProductCategoryElectricalEquipments productCategoryElectricalEquipments;

    public ProductCategory(ProductCategoryElectricalEquipments productCategoryElectricalEquipments) {
        this.productCategoryElectricalEquipments = productCategoryElectricalEquipments;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (productCategoryElectricalEquipments == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("productCategoryShirt"),productCategoryElectricalEquipments);
    }
}
