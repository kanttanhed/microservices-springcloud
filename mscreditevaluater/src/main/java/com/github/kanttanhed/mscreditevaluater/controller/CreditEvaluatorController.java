package com.github.kanttanhed.mscreditevaluater.controller;

import com.github.kanttanhed.mscreditevaluater.domain.entity.*;
import com.github.kanttanhed.mscreditevaluater.domain.exception.CardRequestException;
import com.github.kanttanhed.mscreditevaluater.domain.exception.CustomerDataNotFoundException;
import com.github.kanttanhed.mscreditevaluater.domain.exception.MicroserviceCommunicationException;
import com.github.kanttanhed.mscreditevaluater.domain.service.EvaluateCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity consultCustomerStatus(@RequestParam("cpf") String cpf) {
        try {
            CustomerStatus customerStatus = evaluateCreditService.obtainCustomerStatus(cpf);
            return ResponseEntity.ok(customerStatus);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity performEvaluation(@RequestBody EvaluationData evaluationData){
        try {
            EvaluationCustomerReturn evaluationCustomerReturn =
                    evaluateCreditService.executeEvaluation(evaluationData.getCpf(), evaluationData.getIncome());
            return ResponseEntity.ok(evaluationCustomerReturn);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping(value = "request-card")
    public ResponseEntity cardRequest (@RequestBody DatasRequestIssueCard datas){
        try {
            ProtocolCardRequest protocolCardRequest = evaluateCreditService
                    .requestIssueCard(datas);
            return ResponseEntity.ok(protocolCardRequest);
        }catch (CardRequestException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
