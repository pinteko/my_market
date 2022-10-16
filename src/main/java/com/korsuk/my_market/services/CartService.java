package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.AuthorDto;
import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.products.Cart;
import com.korsuk.my_market.products.Novel;
import com.korsuk.my_market.repo.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<NovelDto> novelsInCart() {
      List<Cart> carts = cartRepository.findAll();
      List<Novel> novels = carts.stream().map(Cart::getNovel).toList();
      List<NovelDto> novelsDto = novels.stream().map(n -> new NovelDto(n.getId(),
                n.getTitle(), new AuthorDto(n.getAuthor().getId(),
                n.getAuthor().getName(), n.getAuthor().getSurname()), n.getRating(), n.getPrice())).collect(Collectors.toList());
      return novelsDto;
    }

    @Transactional
    public void deleteFromCart(Long id) {
        cartRepository.deleteCartByNovel_Id(id);
    }

    public Cart save(Cart cart) {
       return cartRepository.saveAndFlush(cart);
    }
}
