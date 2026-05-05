package idempotent.gateway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import idempotent.gateway.model.IdempotentCheckModel;

public  interface IdempotentRepositoryI extends JpaRepository <IdempotentCheckModel,Long> {

    Optional<IdempotentCheckModel> findByHeaderKey(String key);
    
}
