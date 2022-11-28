package com.korsuk.my_market.converters;

import com.korsuk.my_market.dto.OrderItemDto;
import com.korsuk.my_market.entities.OrderItem;
import com.korsuk.my_market.exceptions.ResourceNotFoundException;
import com.korsuk.my_market.services.NovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {

    private final NovelConverter novelConverter;
    private final NovelService novelService;

    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        return new OrderItem(
                novelService.getNovelById(orderItemDto.getNovelId()).orElseThrow(() -> new ResourceNotFoundException("Novel not found")),
                orderItemDto.getQuantity(),
                orderItemDto.getPricePerProduct(),
                orderItemDto.getPrice()
        );
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getNovel());
    }
}
