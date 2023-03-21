package com.github.kanttanhed.mscreditevaluater.domain.exception;

public class CustomerDataNotFoundException extends Exception{
    public CustomerDataNotFoundException() {
        super("Customer Data not found to the CPF");
    }
}
