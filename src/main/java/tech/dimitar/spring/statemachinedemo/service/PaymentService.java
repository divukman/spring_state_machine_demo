package tech.dimitar.spring.statemachinedemo.service;

import org.springframework.statemachine.StateMachine;
import tech.dimitar.spring.statemachinedemo.domain.Payment;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;

import java.util.UUID;

public interface PaymentService {
    Payment newPayment(Payment payment);
    StateMachine<PaymentState, PaymentEvent> preAuth(UUID paymentId);
    StateMachine<PaymentState, PaymentEvent> authorizePayment(UUID paymentId);
    StateMachine<PaymentState, PaymentEvent> declinePayment(UUID paymentId);
}
