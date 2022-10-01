package com.korsuk.my_market.controllers;

import com.korsuk.my_market.products.Student;
import com.korsuk.my_market.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {

   private final StudentService service;
    //http:localhost:8189/app

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "<h1>hello bro</h1>";
    }

    @GetMapping("/bye")
    @ResponseBody
    public String bye() {
        return "bye bro";
    }

    //http:localhost:8189/app/calc?first=5&second=4
    @GetMapping("/calc")
    @ResponseBody
    public int calc(@RequestParam(name = "first") int a, @RequestParam(required = false, defaultValue = "0", name = "second") int b) {
        return a + b;
    }

    //http:localhost:8189/app/products/12/info
    @GetMapping("/products/{anyName}/info")
    @ResponseBody
    public String info(@PathVariable(name = "anyName") String id) {
        return "Product with id = " + id;
    }

    //http:localhost:8189/app/page
    // приходит запрос
    // создаем модель, которую отдадим в нашу страницу
    //
    @GetMapping("/page")
    public String page(Model model, @RequestParam int id) {
        model.addAttribute("studentFront", service.getStudent(id));
        return "index.html";
    }

 }
