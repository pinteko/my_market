package com.korsuk.my_market.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30 characters")
    private String author;

    @Min(value = 0, message = "Rating should be greater than 0")
    private double rating;

    @Min(value = 0, message = "Price should be greater than 0")
    private double price;

    @Override
    public String toString() {
        return "id:" + id + " " +
                '\"' + title + '\"' +
                " " + author + " (" +
                "rating=" + rating + ").";
    }
}
