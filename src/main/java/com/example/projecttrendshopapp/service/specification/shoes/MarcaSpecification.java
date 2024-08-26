package com.example.projecttrendshopapp.service.specification.shoes;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class MarcaSpecification implements Specification {
    private final String marca;

    public MarcaSpecification(String marca) {
        this.marca = marca;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (marca == null || marca.isBlank()) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.like(criteriaBuilder.lower(root.get("marca")).as(String.class), "%" + marca.toLowerCase() + "%");
    }
}
