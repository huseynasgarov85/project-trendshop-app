package com.example.projecttrendshopapp.service.specification.shoes;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class NameSpecification implements Specification {
    private final String name;

    public NameSpecification(String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (name == null || name.isBlank()) {
            return criteriaBuilder.conjunction();
        }
     return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")).as(String.class),"%" + name.toLowerCase() + "%");
    }
}
