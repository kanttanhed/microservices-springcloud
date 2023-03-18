package com.github.kanttanhed.mscreditevaluater.controller;

import com.github.kanttanhed.mscreditevaluater.domain.entity.CustomerStatus;
import com.github.kanttanhed.mscreditevaluater.domain.service.EvaluateCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditevaluator")
@RequiredArgsConstructor
public class CreditEvaluatorController {
    private final EvaluateCreditService evaluateCreditService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "customer-status", params = "cpf")
    public ResponseEntity<CustomerStatus> consultCustomerStatus(@RequestParam("cpf") String cpf) {
        CustomerStatus customerStatus = evaluateCreditService.obtainCustomerStatus(cpf);
        return ResponseEntity.ok(customerStatus);
    }
}
