package com.github.kanttanhed.mscreditevaluater.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditevaluator")
public class CreditEvaluatorController {
    @GetMapping
    public String status(){
        return "ok";


    }



}
