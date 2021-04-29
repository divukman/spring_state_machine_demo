package tech.dimitar.spring.statemachinedemo.config.actions;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.action.Action;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;

import java.util.Random;

import static tech.dimitar.spring.statemachinedemo.service.PaymentServiceImpl.PAYMENT_ID_HEADER;

//@Configuration
@Deprecated
public class ActionsConfig {
/*
    @Bean("preAuthAction")
    public Action<PaymentState, PaymentEvent> preAuthAction() {
        return stateContext -> {
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
        };
    }*/

/*
    @Bean("authAction")
    public Action<PaymentState, PaymentEvent> authAction() {
        return stateContext -> {
            System.out.println("Auth Called...");

            if (new Random().nextInt(10) < 8) {
                System.out.println("Approved...");
                stateContext.getStateMachine().sendEvent(
                        MessageBuilder
                                .withPayload(PaymentEvent.AUTHORIZE_APPROVED)
                                .setHeader(PAYMENT_ID_HEADER, stateContext.getMessageHeader(PAYMENT_ID_HEADER))
                                .build()
                );
            } else {
                System.out.println("Declined...");
                stateContext.getStateMachine().sendEvent(
                        MessageBuilder
                                .withPayload(PaymentEvent.AUTHORIZE_DECLINED)
                                .setHeader(PAYMENT_ID_HEADER, stateContext.getMessageHeader(PAYMENT_ID_HEADER))
                                .build()
                );
            }
        };
    }*/

/*
    @Bean("preAuthApprovedAction")
    public Action<PaymentState, PaymentEvent> preAuthApprovedAction() {
        return stateContext -> {
            System.out.println("Pre Auth Approved Called...");
        };
    }
*/

/*    @Bean("preAuthDeclinedAction")
    public Action<PaymentState, PaymentEvent> preAuthDeclinedAction() {
        return stateContext -> {
            System.out.println("Pre Auth Declined Called...");
        };
    }*/

/*    @Bean("authApprovedAction")
    public Action<PaymentState, PaymentEvent> authApprovedAction() {
        return stateContext -> {
            System.out.println("Auth Approved Called...");
        };
    }*/

/*    @Bean("authDeclinedAction")
    public Action<PaymentState, PaymentEvent> authDeclinedAction() {
        return stateContext -> {
            System.out.println("Auth Declined Called...");
        };
    }*/
}
