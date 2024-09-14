package com.example.projecttrendshopapp.service.specification.shirts;

import com.example.projecttrendshopapp.enums.ProductCategoryShirt;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategory implements Specification {
    private final ProductCategoryShirt productCategoryShirt;

    public ProductCategory(ProductCategoryShirt productCategoryShirt) {
        this.productCategoryShirt = productCategoryShirt;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (productCategoryShirt == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("productCategoryShirt"),productCategoryShirt);
    }
}
