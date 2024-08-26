package com.example.projecttrendshopapp.service.specification.trousers;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CounterSpecification implements Specification {
    private final Long counter;

    public CounterSpecification(Long counter) {
        this.counter = counter;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (counter == null){
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("counter"),counter);
    }
}
