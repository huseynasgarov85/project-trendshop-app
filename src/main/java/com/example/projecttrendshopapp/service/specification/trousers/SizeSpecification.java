package com.example.projecttrendshopapp.service.specification.trousers;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class SizeSpecification implements Specification {
    private final Integer size;

    public SizeSpecification(Integer size) {
        this.size = size;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (size == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("size"),size);
    }
}
