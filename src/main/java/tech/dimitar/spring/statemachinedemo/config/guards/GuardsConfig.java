package tech.dimitar.spring.statemachinedemo.config.guards;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.guard.Guard;
import tech.dimitar.spring.statemachinedemo.domain.PaymentEvent;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;

import static tech.dimitar.spring.statemachinedemo.service.PaymentServiceImpl.PAYMENT_ID_HEADER;

//@Configuration
@Deprecated
public class GuardsConfig {

/*    @Bean("paymentIdGuard")
    // Makes sure that the event header carries the event ID object
    public Guard<PaymentState, PaymentEvent> paymentIdGuard() {
        return stateContext -> stateContext.getMessageHeader(PAYMENT_ID_HEADER) != null;
    }*/
}
