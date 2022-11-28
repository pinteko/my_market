package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.OrderDetailsDto;
import com.korsuk.my_market.dto.OrderDto;
import com.korsuk.my_market.entities.User;
import com.korsuk.my_market.exceptions.ResourceNotFoundException;
import com.korsuk.my_market.services.OrderService;
import com.korsuk.my_market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

   private final UserService userService;

   private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createOrder(user, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
       return orderService.findOrdersByUsername(principal.getName());
    }
}
