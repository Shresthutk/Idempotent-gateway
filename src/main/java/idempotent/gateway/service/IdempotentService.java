package idempotent.gateway.service;
import java.util.Optional;
import org.springframework.stereotype.Service;

import idempotent.gateway.model.IdempotentCheckModel;
import idempotent.gateway.repository.IdempotentRepositoryI;
import idempotent.gateway.util.Status;

@Service
public class IdempotentService {

    private IdempotentRepositoryI idempotentRepo;

    public IdempotentService (IdempotentRepositoryI idempotentRepo){
        this.idempotentRepo = idempotentRepo;
    }

    public Optional <IdempotentCheckModel> findByKey (String key){
        return idempotentRepo.findByHeaderKey(key);
    }

    public void setProcessing (String key) {
        IdempotentCheckModel idempotentCheck = new IdempotentCheckModel(key, Status.PROCESSING);
        idempotentRepo.save(idempotentCheck);
    }

    public void markSuccess  (String key){
        IdempotentCheckModel idempotentRecord = findByKey(key).orElseThrow(()->new RuntimeException("Key not found"));

        idempotentRecord.setStatus(Status.SUCCESS);
        idempotentRepo.save(idempotentRecord);
    }
    
}
