package com.pavikumbhar.jpa.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Predicate;

public class EntitySpecification {

    public static <T> Specification<T> textInAllColumns(String text) {

        return (Specification<T>) (root, cq, builder) ->{
            if (ObjectUtils.isEmpty(text)) {
                return null; // or criteriaBuilder.conjunction()
            }

            return builder.or(root.getModel()
                        .getDeclaredSingularAttributes()
                        .stream()
                        .filter(a -> a.getJavaType().getSimpleName().equalsIgnoreCase("string"))
                        .map(a -> builder.like(root.get(a.getName()), "%" + text + "%")).toArray(Predicate[]::new)
                );
        };
    }
}