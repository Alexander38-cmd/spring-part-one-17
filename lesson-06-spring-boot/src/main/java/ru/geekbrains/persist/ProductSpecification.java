package ru.geekbrains.persist;

import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecification {
    public static Specification<Product> titlePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("title"), prefix + "%");
    }

    public static  Specification<Product> minPrice(Integer minPrice) {
        return (root, query, builder) -> builder.ge(root.get("price"), minPrice);
    }

    public static  Specification<Product> maxPrice(Integer maxPrice) {
        return (root, query, builder) -> builder.le(root.get("price"), maxPrice);
    }
}
