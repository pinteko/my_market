package com.korsuk.my_market.services;


import com.korsuk.my_market.dto.AspectResponse;
import com.korsuk.my_market.dto.StringResponse;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class AspectService {

    private List<AspectResponse> aspectResponses;
    private AspectResponse novelAspect;
    private AspectResponse authorAspect;
    private AspectResponse cartAspect;
    private AspectResponse orderAspect;

    @PostConstruct
    public void init() {
        aspectResponses = new ArrayList<>();
        aspectResponses.add(0, novelAspect = new AspectResponse("NovelService"));
        aspectResponses.add(1, authorAspect = new AspectResponse("AuthorService"));
        aspectResponses.add(2, cartAspect = new AspectResponse("CartService"));
        aspectResponses.add(3, orderAspect = new AspectResponse("OrderService"));
    }

    public List<AspectResponse> getStatisticAboutWorkServices() {
        return aspectResponses;
    }

    @Around("execution(public * com.korsuk.my_market.services.NovelService.*(..))")
    public Object getWorkingTimeFromNovelService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        aspectResponses.get(0).setWorkingTime(end - begin);
        return out;
    }

    @Around("execution(public * com.korsuk.my_market.services.AuthorService.*(..))")
    public Object getWorkingTimeFromAuthorService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        aspectResponses.get(1).setWorkingTime(end - begin);
        return out;
    }

    @Around("execution(public * com.korsuk.my_market.services.CartService.*(..))")
    public Object getWorkingTimeFromCartService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        aspectResponses.get(2).setWorkingTime(end - begin);
        return out;
    }

    @Around("execution(public * com.korsuk.my_market.services.OrderService.*(..))")
    public Object getWorkingTimeFromOrderService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        aspectResponses.get(3).setWorkingTime(end - begin);
        return out;
    }
}
