package com.grupo9.SppringApp004D.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/Hola")
    public String hola() {
        return "Hola mundo";
    }
}
