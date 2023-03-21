package com.github.kanttanhed.mscreditevaluater.domain.exception;

import lombok.Getter;

public class MicroserviceCommunicationException extends Exception {

    @Getter
    private Integer status;

    public MicroserviceCommunicationException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
