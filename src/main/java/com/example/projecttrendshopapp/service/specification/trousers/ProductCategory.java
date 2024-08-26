package com.example.projecttrendshopapp.service.specification.trousers;

import com.example.projecttrendshopapp.model.enums.ProductCategoryShoes;
import com.example.projecttrendshopapp.model.enums.ProductCategoryTrousers;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategory implements Specification {
    private final ProductCategoryTrousers productCategoryTrousers;

    public ProductCategory(ProductCategoryTrousers productCategoryTrousers) {
        this.productCategoryTrousers = productCategoryTrousers;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (productCategoryTrousers == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("productCategoryShoes"),productCategoryTrousers);
    }
}
