package com.example.testapp.controllers;

import com.example.testapp.services.CryptoCurrenciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class CryptoCurrenciesController {

    private final CryptoCurrenciesService service;

    public CryptoCurrenciesController(CryptoCurrenciesService service) {
        this.service = service;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("model", "Model");
        return "index";
    }

    @GetMapping("parse")
    public String parse(Model model) throws IllegalAccessException {
        service.saveData();
        return "index";
    }

    @GetMapping("show")
    public String show(Model model) {
        model.addAttribute("currencies" ,service.getData());
        return "index";
    }


}
