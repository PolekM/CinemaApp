package com.cinema.dto.movie;

import lombok.Data;

import java.util.List;

@Data
public class MoviePageableDto {

    private List<MovieReadDto> content;
    private int currentPage;
    private int totalPage;
    private long totalElements;

    public MoviePageableDto(List<MovieReadDto> list, int currentPage, int totalPage, long totalElements) {
        this.content = list;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
    }
}
