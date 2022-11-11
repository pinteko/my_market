package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.products.Cart;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.repo.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartNotEntity cartNotEntity;

    public List<NovelDto> novelsInCart() {
      List<Cart> carts = cartRepository.findAll();
      List<Novel> novels = carts.stream().map(Cart::getNovel).toList();
      List<NovelDto> novelsDto = novels.stream().map(NovelDto::new).collect(Collectors.toList());
      return novelsDto;
    }

    public Set<NovelDto> novelsInCartNotEntity() {
        return cartNotEntity.cartNovels(cartNotEntity.getNovelsInCart());
    }

//    @Transactional
    public void deleteFromCart(NovelDto novelDto) {
//        cartRepository.deleteCartByNovel_Id(id);
        cartNotEntity.deleteFromCart(novelDto);
    }

    public Cart save(Cart cart) {
       return cartRepository.save(cart);
    }
}
