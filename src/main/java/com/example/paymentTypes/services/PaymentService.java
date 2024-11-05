package com.example.paymentTypes.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.paymentTypes.pojo.PaymentType;
import com.example.paymentTypes.repository.PaymentTypeRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentTypeRepository paymentRepository;

    public void createPayment(String paymentType, MultipartFile file, PaymentType transactionData) throws IOException {
        byte[] fileData = file.getBytes();
        transactionData.setFileData(fileData);
        transactionData.setTransactionId(generateTransactionId());
        transactionData.setAmount(transactionData.getAmount());
        paymentRepository.save(transactionData);
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public PaymentType getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public List<PaymentType> getAllTransactions() {
        return paymentRepository.findAll();
    }
}
