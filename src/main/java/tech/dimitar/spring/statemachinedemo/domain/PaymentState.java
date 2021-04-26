package tech.dimitar.spring.statemachinedemo.domain;

// states for the state machine
public enum PaymentState {
    NEW,

    PRE_AUTH,
    PRE_AUTH_ERROR,

    AUTH,
    AUTH_ERROR
}
