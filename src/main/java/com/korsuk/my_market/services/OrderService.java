package com.korsuk.my_market.services;

import com.korsuk.my_market.converters.OrderConverter;
import com.korsuk.my_market.dto.CartNotEntity;
import com.korsuk.my_market.dto.OrderDetailsDto;
import com.korsuk.my_market.dto.OrderDto;
import com.korsuk.my_market.entities.Order;
import com.korsuk.my_market.entities.OrderItem;
import com.korsuk.my_market.entities.User;
import com.korsuk.my_market.exceptions.ResourceNotFoundException;
import com.korsuk.my_market.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderConverter orderConverter;
    private final NovelService novelService;

    @Transactional
    public void createOrder(User user, OrderDetailsDto orderDetailsDto) {
        CartNotEntity currentCart = cartService.getCurrentCart();

        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());

        List<OrderItem> items = currentCart.getNovelsInCart().stream()
                .map(orderItemDto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setQuantity(orderItemDto.getQuantity());
                    orderItem.setPricePerProduct(orderItemDto.getPricePerProduct());
                    orderItem.setPrice(orderItemDto.getPrice());
                    orderItem.setNovel(novelService.getNovelById(orderItemDto.getNovelId())
                            .orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + orderItemDto.getNovelId())));
                    return orderItem;
                })
                .collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        cartService.clear();
    }

    public List<OrderDto> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
