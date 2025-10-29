package com.transportsystem.service;
import org.springframework.stereotype.Service;

@Service
public class TestService {
   public String test(String name) {
       return "Hello world from Service!" + name;
   }
}