package com.example.paymentTypes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.paymentTypes.pojo.PaymentType;
import com.example.paymentTypes.services.PaymentService;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public ResponseEntity<String> createPayment(@RequestParam("payment_type") String paymentType,
            @RequestPart("file") MultipartFile file,
            @RequestPart("transactionData") PaymentType transactionData) {
        try {
            paymentService.createPayment(paymentType, file, transactionData);
            return ResponseEntity.status(HttpStatus.CREATED).body("Payment transaction created successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating payment transaction: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentType> getPaymentById(@PathVariable Long id) {
        try {
            PaymentType payment = paymentService.getPaymentById(id);
            if (payment != null) {
                return ResponseEntity.ok(payment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/transactions")
    public ResponseEntity<List<PaymentType>> getAllPayment() {
        try {
            List<PaymentType> payment = paymentService.getAllTransactions();
            if (payment != null) {
                return ResponseEntity.ok(payment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

}
