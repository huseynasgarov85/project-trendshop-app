package com.example.projecttrendshopapp.service.specification.shoes;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BalanceSpecification implements Specification {
    private final Double balance;

    public BalanceSpecification(Double balance) {
        this.balance = balance;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
        if (balance == null) {
            return criteriaBuilder.conjunction();
        }
        return criteriaBuilder.equal(root.get("balance"), balance);
    }
}
