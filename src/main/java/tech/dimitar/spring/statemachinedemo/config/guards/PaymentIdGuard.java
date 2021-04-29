package tech.dimitar.spring.statemachinedemo.config.guards;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;

import static tech.dimitar.spring.statemachinedemo.service.PaymentServiceImpl.PAYMENT_ID_HEADER;

@Component
public class PaymentIdGuard implements Guard<PaymentState, PaymentEvent> {
    @Override
    public boolean evaluate(StateContext<PaymentState, PaymentEvent> stateContext) {
        return stateContext.getMessageHeader(PAYMENT_ID_HEADER) != null;
    }
}
