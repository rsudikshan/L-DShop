package com.sr.L.DShop.controllers;

import com.sr.L.DShop.models.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webhook")
public class WebHookController {

   // private final PaymentServiceImpl paymentService;

    @PostMapping("/payment-status")
     //@RequestBody PaymentRequest paymentRequest
    public ResponseModel handlePaymentWebhook() {
        // Assuming the webhook contains the order ID, status, and payment reference
//        return paymentService.processPayment(paymentRequest.getOrderId(), paymentRequest);
        return null;
    }
}
