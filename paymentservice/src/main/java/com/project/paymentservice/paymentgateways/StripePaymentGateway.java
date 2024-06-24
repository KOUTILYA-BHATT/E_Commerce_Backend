package com.project.paymentservice.paymentgateways;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public String generatePaymentLink(String orderId, Long amount, String email, String phoneNumber) {
        return "";
    }
}
