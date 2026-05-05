package idempotent.gateway.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import idempotent.gateway.model.PaymentModel;

public interface PaymentRepositoryI   extends JpaRepository <PaymentModel, Long>{
    
}
