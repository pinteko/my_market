package com.korsuk.my_market.validators;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NovelValidator {
    public void validate(NovelDto novelDto) {
        List<String> errors = new ArrayList<>();
        if (novelDto.getPrice() < 1) {
            errors.add("Цена книги не может быть меньше 1");
        }
        if (novelDto.getTitle().isBlank()) {
            errors.add("Книга не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
