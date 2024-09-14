package com.example.projecttrendshopapp.service.specification.trousers;

import com.example.projecttrendshopapp.enums.ProductCategoryTrousers;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategory implements Specification<ProductCategory> {
    private final ProductCategoryTrousers productCategoryTrousers;

    public ProductCategory(ProductCategoryTrousers productCategoryTrousers) {
        this.productCategoryTrousers = productCategoryTrousers;
    }

    @Override
    public Predicate toPredicate(@Nullable Root root,@Nullable CriteriaQuery query,@Nullable CriteriaBuilder criteriaBuilder) {
        if (productCategoryTrousers == null) {
            assert criteriaBuilder != null;
            return criteriaBuilder.conjunction();
        }
        assert criteriaBuilder != null;
        assert root != null;
        return criteriaBuilder.equal(root.get("productCategoryShoes"),productCategoryTrousers);
    }
}
