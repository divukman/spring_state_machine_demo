package tech.dimitar.spring.statemachinedemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.dimitar.spring.statemachinedemo.domain.Payment;
import tech.dimitar.spring.statemachinedemo.domain.PaymentState;
import tech.dimitar.spring.statemachinedemo.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .id(UUID.randomUUID())
                .amount(BigDecimal.valueOf(100L))
                //.paymentState(PaymentState.NEW)
                .build();
    }

    @Test
    void preAuth() {
        final Payment savedPayment = paymentService.newPayment(payment);

        paymentService.preAuth(savedPayment.getId());

        paymentRepository.findById(savedPayment.getId())
                .ifPresent(persistedPayment -> {
                    System.out.println(persistedPayment);
                    System.out.println(persistedPayment.getPaymentState());
                });
    }

    @Test
    @RepeatedTest(10)
    void auth() {
        final Payment savedPayment = paymentService.newPayment(payment);

        paymentService.preAuth(savedPayment.getId());

        paymentRepository.findById(savedPayment.getId())
                .ifPresent(persistedPayment -> {
                    if (persistedPayment.getPaymentState() == PaymentState.PRE_AUTH) {
                        paymentService.authorizePayment(persistedPayment.getId());
                    }
                });

    }
}