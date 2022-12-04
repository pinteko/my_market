package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.NovelDto;
import com.korsuk.my_market.dto.CartNotEntity;
import com.korsuk.my_market.dto.OrderItemDto;
import com.korsuk.my_market.dto.StringResponse;
import com.korsuk.my_market.services.CartService;
import com.korsuk.my_market.services.NovelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
@Slf4j
public class CartController {

    private final CartService cartService;
    private final NovelService novelService;


//    @GetMapping()
//    public List<NovelDto> getNovels() {
//        return cartService.novelsInCart();
//    }

    @GetMapping("/{uuid}")
    public CartNotEntity getCurrentCart(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        return cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{novelId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long novelId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.addNovelInCart(getCurrentCartUuid(username, uuid), novelId);
    }

    @GetMapping("/{uuid}/decrement/{novelId}")
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long novelId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.decrementItem(getCurrentCartUuid(username, uuid), novelId);
    }

    @DeleteMapping("/{uuid}/remove/{novelId}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long novelId) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.deleteFromCart(getCurrentCartUuid(username, uuid), novelId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.clearCart(getCurrentCartUuid(username, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }

}
