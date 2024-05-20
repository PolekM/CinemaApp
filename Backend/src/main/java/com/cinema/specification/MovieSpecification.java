package com.cinema.specification;

import com.cinema.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<Movie> containTextInMovieName(String text){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),"%"+ text.toLowerCase() +"%"));

    }
}
