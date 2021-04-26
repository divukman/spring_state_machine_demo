package tech.dimitar.spring.statemachinedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.dimitar.spring.statemachinedemo.domain.Payment;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
