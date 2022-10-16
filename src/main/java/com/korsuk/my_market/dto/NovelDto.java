package com.korsuk.my_market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NovelDto {

    private Long id;

    private String title;

    private AuthorDto author;

    private Double rating;

    private Double price;
}