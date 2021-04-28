package tech.dimitar.spring.statemachinedemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;
import tech.dimitar.spring.statemachinedemo.domain.Payment;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;
import tech.dimitar.spring.statemachinedemo.repository.PaymentRepository;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentStateChangeInterceptor extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {

    final PaymentRepository paymentRepository;


    @Override
    public void preStateChange(State<PaymentState, PaymentEvent> state,
                               Message<PaymentEvent> message,
                               Transition<PaymentState, PaymentEvent> transition,
                               StateMachine<PaymentState, PaymentEvent> stateMachine,
                               StateMachine<PaymentState, PaymentEvent> rootStateMachine) {

        Optional.ofNullable(message).ifPresent(paymentEventMessage -> {
            Optional.ofNullable(
                    paymentEventMessage.getHeaders().getOrDefault(PaymentServiceImpl.PAYMENT_ID_HEADER, null)
            ).ifPresent(o -> {
                final UUID id = UUID.class.cast(o);
                final Payment payment = paymentRepository.getOne(id);
                payment.setPaymentState(state.getId());
                paymentRepository.save(payment);
            });
        });

    }
}
