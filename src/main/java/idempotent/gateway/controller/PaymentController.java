package idempotent.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idempotent.gateway.dto.RequestDto;
import idempotent.gateway.service.BuisnessService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    BuisnessService buisnessService;

    public PaymentController(BuisnessService buisnessService){
        this.buisnessService = buisnessService;
    }

    @PostMapping
    public void payment(@RequestBody RequestDto request){
        buisnessService.processPayment(request);
    }

}
