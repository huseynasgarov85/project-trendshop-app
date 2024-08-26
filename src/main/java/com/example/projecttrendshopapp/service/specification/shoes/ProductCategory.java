package com.example.projecttrendshopapp.service.specification.shoes;

import com.example.projecttrendshopapp.model.enums.ProductCategoryShirt;
import com.example.projecttrendshopapp.model.enums.ProductCategoryShoes;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategory implements Specification {
    private final ProductCategoryShoes productCategoryShoes;

    public ProductCategory(ProductCategoryShoes productCategoryShoes) {
        this.productCategoryShoes = productCategoryShoes;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (productCategoryShoes == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("productCategoryShoes"),productCategoryShoes);
    }
}
