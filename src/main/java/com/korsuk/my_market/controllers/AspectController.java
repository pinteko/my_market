package com.korsuk.my_market.controllers;

import com.korsuk.my_market.dto.AspectResponse;
import com.korsuk.my_market.services.AspectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class AspectController {

    private final AspectService aspectService;

    @GetMapping
    public List<AspectResponse> getStatisticAboutWorkServices() {
        return aspectService.getStatisticAboutWorkServices();
    }
}
