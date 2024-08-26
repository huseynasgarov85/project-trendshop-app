package com.example.projecttrendshopapp.service.specification.shirts;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SizeSpecification implements Specification {
    private final String size;

    public SizeSpecification(String size) {
        this.size = size;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (size == null || size.isBlank()) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("size")).as(String.class), "%" + size.toLowerCase() + "%");
    }
}
