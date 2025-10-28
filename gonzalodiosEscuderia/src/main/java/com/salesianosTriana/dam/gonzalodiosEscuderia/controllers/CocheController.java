package com.salesianosTriana.dam.gonzalodiosEscuderia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CocheController {

    @GetMapping("/")
    public String heroSection(Model model){
        return "heroSection";
    }

    @GetMapping("/dashboard")
    public String index(Model model){
        return "dashboard";
    }

}
