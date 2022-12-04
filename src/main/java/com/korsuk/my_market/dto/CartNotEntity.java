package com.korsuk.my_market.dto;

import com.korsuk.my_market.products.Novel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisHash;

import java.util.*;

@Data
@Slf4j
public class CartNotEntity {

   private List<OrderItemDto> novelsInCart;
   private double totalPrice;

   public CartNotEntity() {
      this.novelsInCart = new ArrayList<>();
   }



   public void addInCart(Novel novel) {
      if (isInCart(novel.getId())) {
         return;
      }
      novelsInCart.add(new OrderItemDto(novel));
      recalculate();
   }

   public boolean isInCart(Long id) {
      for (OrderItemDto o : novelsInCart) {
         if (o.getNovelId().equals(id)) {
            o.changeQuantity(1);
            recalculate();
            return true;
         }
      }
      return false;
   }

   public void decrement(Long id) {
      Iterator<OrderItemDto> iter = novelsInCart.iterator();
      while (iter.hasNext()) {
         OrderItemDto o = iter.next();
         if (o.getNovelId().equals(id)) {
            o.changeQuantity(-1);
            if (o.getQuantity() <= 0) {
               iter.remove();
            }
            recalculate();
            return;
         }
      }
   }

   public void removeNovel(Long id) {
      novelsInCart.removeIf(o -> o.getNovelId().equals(id));
      recalculate();
   }

   private void recalculate() {
      totalPrice = 0;
      for (OrderItemDto o : novelsInCart) {
         totalPrice += o.getPrice();
      }
   }

   public void clearCart() {
      novelsInCart.clear();
      totalPrice = 0;
   }

   public void merge(CartNotEntity another) {
      for (OrderItemDto anotherItem : another.novelsInCart) {
         boolean merged = false;
         for (OrderItemDto myItem : novelsInCart) {
            if (myItem.getNovelId().equals(anotherItem.getNovelId())) {
               myItem.changeQuantity(anotherItem.getQuantity());
               merged = true;
               break;
            }
         }
         if (!merged) {
            novelsInCart.add(anotherItem);
         }
      }
      recalculate();
      another.clearCart();
   }




}
