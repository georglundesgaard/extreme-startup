package no.lundesgaard.startup.extreme.question.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QuestionType {
    ADDITION("add"),
    MAXIMUM("max"),
    MULTIPLICATION("mult"),
    SQUARE_CUBE("sq_cb"),
    GENERAL_KNOWLEDGE("general"),
    PRIMES("primes"),
    SUBTRACTION("sub"),
    FIBONACCI("fib"),
    POWER("pow"),
    ADDITION_ADDITION("add_add"),
    ADDITION_MULTIPLICATION("add_mult"),
    MULTIPLICATION_ADDITION("mult_add"),
    WARMUP("warmup");
    
    private final String typeName;
}
