package com.project.paymentservice.controllers;

import com.project.paymentservice.dtos.InitiatePaymentDto;
import com.project.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentDto paymentDto) {
        return paymentService.initiatePayment(paymentDto.getOrderId(),
                paymentDto.getAmount(),
                paymentDto.getEmail(),
                paymentDto.getPhoneNumber());
    }
}
