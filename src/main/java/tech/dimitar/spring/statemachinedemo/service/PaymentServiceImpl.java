package tech.dimitar.spring.statemachinedemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import tech.dimitar.spring.statemachinedemo.domain.Payment;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;
import tech.dimitar.spring.statemachinedemo.exception.NotFoundException;
import tech.dimitar.spring.statemachinedemo.repository.PaymentRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    public static String PAYMENT_ID_HEADER = "payment_id";

    private final PaymentRepository paymentRepository;
    private final StateMachineFactory<PaymentState, PaymentEvent> stateMachineFactory;
    private final PaymentStateChangeInterceptor paymentStateChangeInterceptor;

    @Transactional
    @Override
    public Payment newPayment(Payment payment) {
        payment.setPaymentState(PaymentState.NEW);
        return paymentRepository.save(payment);
    }

    @Transactional
    @Override
    public StateMachine<PaymentState, PaymentEvent> preAuth(UUID paymentId) {
        final StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);

        sendEvent(paymentId, sm, PaymentEvent.PRE_AUTH_APPROVED);

        return sm;
    }

    @Transactional
    @Override
    public StateMachine<PaymentState, PaymentEvent> authorizePayment(UUID paymentId) {
        final StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);

        sendEvent(paymentId, sm, PaymentEvent.AUTHORIZE_APPROVED);
        return sm;
    }

    @Transactional
    @Override
    public StateMachine<PaymentState, PaymentEvent> declinePayment(UUID paymentId) {
        final StateMachine<PaymentState, PaymentEvent> sm = build(paymentId);

        sendEvent(paymentId, sm, PaymentEvent.AUTHORIZE_DECLINED);

        return sm;
    }

    private StateMachine<PaymentState, PaymentEvent> build (final UUID paymentId) {
        final Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new NotFoundException("Payment with ID: " + paymentId.toString() + " not found."));

        StateMachine<PaymentState, PaymentEvent> statePaymentEventStateMachine = stateMachineFactory.getStateMachine(paymentId);
        statePaymentEventStateMachine.stop();
        statePaymentEventStateMachine.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(paymentStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(payment.getPaymentState(), null, null, null));
                });

        statePaymentEventStateMachine.start();
        return statePaymentEventStateMachine;
    }

    private void sendEvent(final UUID paymentId, final StateMachine<PaymentState, PaymentEvent> sm, final PaymentEvent paymentEvent) {
        final Message message = MessageBuilder
                .withPayload(paymentEvent)
                .setHeader(PAYMENT_ID_HEADER, paymentId)
                .build();

        sm.sendEvent(message);
    }
}
