package com.korsuk.my_market.repo.specification;

import com.korsuk.my_market.products.Novel;
import org.springframework.cglib.core.Predicate;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
}
