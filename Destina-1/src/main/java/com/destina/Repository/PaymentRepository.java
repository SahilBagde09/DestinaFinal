package com.destina.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.destina.model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByCustomerId(Long customerId);
    List<Payment> findByAgentId(Long agentId);
    Optional<Payment> findByBooking_BookingId(Long bookingId);
}
