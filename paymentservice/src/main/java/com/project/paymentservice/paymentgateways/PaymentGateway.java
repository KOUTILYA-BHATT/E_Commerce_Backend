package com.project.paymentservice.paymentgateways;

public interface PaymentGateway {

    public String generatePaymentLink(String orderId, Long amount, String email, String phoneNumber);
}
