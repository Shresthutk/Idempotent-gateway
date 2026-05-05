package idempotent.gateway.service;

import idempotent.gateway.dto.RequestDto;
import idempotent.gateway.dto.ResponseDto;
import idempotent.gateway.model.PaymentModel;
import idempotent.gateway.repository.PaymentRepositoryI;
import idempotent.gateway.util.Status;

import java.util.UUID;
import org.springframework.stereotype.Service;


@Service
public class BuisnessService {

    
    private PaymentRepositoryI paymentRepo;

    public BuisnessService(PaymentRepositoryI paymentRepo){
        this.paymentRepo = paymentRepo;
    }

    public ResponseDto processPayment(RequestDto request){

        String id = UUID.randomUUID().toString();
        PaymentModel payment = new PaymentModel(id,request.getCurrency(),request.getAmount(),Status.SUCCESS);
        paymentRepo.save(payment);
        return new ResponseDto(Status.SUCCESS);
    }
    
}
