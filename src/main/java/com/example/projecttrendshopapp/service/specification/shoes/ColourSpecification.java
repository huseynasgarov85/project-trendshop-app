package com.example.projecttrendshopapp.service.specification.shoes;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ColourSpecification implements Specification {
    private final String colour;

    public ColourSpecification(String colour) {
        this.colour = colour;
    }

    @Override
    public Predicate toPredicate(Root root, @Nullable CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (colour == null || colour.isBlank()) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("colour")).as(String.class), "%" + colour.toLowerCase() + "%");
    }
}
