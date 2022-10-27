package com.korsuk.my_market.repo.specification;

import com.korsuk.my_market.products.Novel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class NovelSpecification {

    public static Specification<Novel> ratingGreaterThanOrEqualTo(Double rating) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating));
    }

    public static Specification<Novel> ratingLessThanOrEqualTo(Double rating) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("rating"), rating));
    }

    public static Specification<Novel> priceGreaterThanOrEqualTo(Double price) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Novel> priceLessThanOrEqualTo(Double price) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Novel> titleLike(String titlePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)));
    }

    public static Specification<Novel> authorNameLike(String authorNamePart) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("authorName"), String.format("%%%s%%", authorNamePart)));
    }

    public static Specification<Novel> orderById() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.orderBy(criteriaBuilder.asc(root.get("id")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
