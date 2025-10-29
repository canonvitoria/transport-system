package com.transportsystem.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transportsystem.service.TestService;

@RestController
@RequestMapping("/test")
// STATELESS -> a cada nova requisição, recebo todas as requisições para fazer a funcionalidade que o cliente esté pedindo (token)

//STATEFULL -> o estado de cada cliente é mantido no servidor
public class TestController {

    @Autowired
    private TestService testService;

    //post, get, put, delete, patch, options, head
    @GetMapping
    public String test() {
        return testService.test(" Vitória");
    }
}