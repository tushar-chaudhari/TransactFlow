package com.nitk.PaymentService.command.api.events;

import com.nitk.CommonService.events.PaymentCancelledEvent;
import com.nitk.CommonService.events.PaymentProcessedEvent;
import com.nitk.PaymentService.command.api.data.Payment;
import com.nitk.PaymentService.command.api.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentsEventHandler {

    private PaymentRepository paymentRepository;

    public PaymentsEventHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        Payment payment
                = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("COMPLETED")
                .timeStamp(new Date())
                .build();

        paymentRepository.save(payment);
    }

    @EventHandler
    public void on(PaymentCancelledEvent event) {
        Payment payment
                = paymentRepository.findById(event.getPaymentId()).get();

        payment.setPaymentStatus(event.getPaymentStatus());

        paymentRepository.save(payment);
    }
}
