package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.CartNotEntity;
import com.korsuk.my_market.exceptions.ResourceNotFoundException;
import com.korsuk.my_market.products.Novel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final NovelService novelService;

    private CartNotEntity cart;

    @PostConstruct
    public void init() {
        cart = new CartNotEntity();
    }

    public CartNotEntity getCurrentCart() {
        return cart;
    }

    public void addNovelInCart(Long novelId) {
        if (!getCurrentCart().isInCart(novelId)) {
            Novel novel = novelService.getNovelById(novelId).orElseThrow(() -> new ResourceNotFoundException("Novel not found with id: " + novelId));
            getCurrentCart().addInCart(novel);
        }
    }

    public void clear() {
        cart.clearCart();
    }

    public void deleteFromCart(Long novelId) {
        cart.deleteFromCart(novelId);
    }
}
