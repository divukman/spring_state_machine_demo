package tech.dimitar.spring.statemachinedemo.config.actions;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;

import java.util.Random;

import static tech.dimitar.spring.statemachinedemo.service.PaymentServiceImpl.PAYMENT_ID_HEADER;

@Component
public class PreAuthAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
        System.out.println("Pre Auth Called...");

        if (new Random().nextInt(10) < 8) {
            System.out.println("Approved...");
            stateContext.getStateMachine().sendEvent(
                    MessageBuilder
                            .withPayload(PaymentEvent.PRE_AUTH_APPROVED)
                            .setHeader(PAYMENT_ID_HEADER, stateContext.getMessageHeader(PAYMENT_ID_HEADER))
                            .build()
            );
        } else {
            System.out.println("Declined...");
            stateContext.getStateMachine().sendEvent(
                    MessageBuilder
                            .withPayload(PaymentEvent.PRE_AUTH_DECLINED)
                            .setHeader(PAYMENT_ID_HEADER, stateContext.getMessageHeader(PAYMENT_ID_HEADER))
                            .build()
            );
        }
    }
}
