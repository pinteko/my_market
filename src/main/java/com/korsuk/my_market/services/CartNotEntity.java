package com.korsuk.my_market.services;

import com.korsuk.my_market.dto.NovelDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CartNotEntity {

   private Map<NovelDto, Integer> novelsInCart;

   @PostConstruct
   public void init() {
      novelsInCart = new HashMap<>();
   }



   public void addInCart(NovelDto novelDto) {
      if (novelsInCart.containsKey(novelDto)) {
         int x = novelsInCart.get(novelDto) + 1;
         novelsInCart.put(novelDto, x);
      }
      else {
         novelsInCart.put(novelDto, 1);
      }
      log.info(novelsInCart.toString());
   }

   public void deleteFromCart(NovelDto novelDto) {
      log.info(novelsInCart.toString() + "all in cart");
      log.info(novelsInCart.get(novelDto).toString());
      if (novelsInCart.containsKey(novelDto)) {
         int x = novelsInCart.get(novelDto) - 1;
         if (x == 0) {
            novelsInCart.remove(novelDto);
         }
         else {
            novelsInCart.put(novelDto, x);
            log.info(novelsInCart.toString() + "all in cart");
         }
      }
   }

   public Set<NovelDto> cartNovels (Map<NovelDto, Integer> novelsInCart) {
      novelsInCart.forEach(NovelDto::setCount);
      return novelsInCart.keySet();
   }




}
