package tech.dimitar.spring.statemachinedemo.domain;


// Events for the state machine
public enum PaymentEvent {
    PRE_AUTHORIZE,
    PRE_AUTH_APPROVED,
    PRE_AUTH_DECLINED,

    AUTHORIZE,
    AUTHORIZE_APPROVED,
    AUTHORIZE_DECLINED
}
