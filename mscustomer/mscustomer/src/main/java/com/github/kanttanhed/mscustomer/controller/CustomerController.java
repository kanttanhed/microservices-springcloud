package com.github.kanttanhed.mscustomer.controller;

import com.github.kanttanhed.mscustomer.domain.dtos.CustomerDTO;
import com.github.kanttanhed.mscustomer.domain.entity.Customer;
import com.github.kanttanhed.mscustomer.domain.repository.CustomerRepository;
import com.github.kanttanhed.mscustomer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CustomerDTO customerDTO){

      var customer = customerDTO.toModel();
      customerService.save(customer);
      URI headerLocation = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .query("cpf={cpf}")
              .buildAndExpand(customer.getCpf())
              .toUri();
      return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = customerService.getByCPF(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
}
