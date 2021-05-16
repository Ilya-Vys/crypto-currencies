package com.example.testapp.controllers;

import com.example.testapp.services.CryptoCurrenciesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class CryptoCurrenciesController {

    private final CryptoCurrenciesService currenciesService;

    public CryptoCurrenciesController(CryptoCurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }

    @GetMapping()
    public String index(){
        return "index";
    }

    @GetMapping("parse")
    public String parse() throws IllegalAccessException {
        currenciesService.saveData();
        return "index";
    }

    @GetMapping("show")
    public String show(Model model) {
        model.addAttribute("currencies" , currenciesService.getData());
        return "index";
    }


}
