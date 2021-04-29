package tech.dimitar.spring.statemachinedemo.config.actions;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;

@Component
public class PreAuthApprovedAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> stateContext) {
        System.out.println("Pre Auth Approved Called...");
    }
}
