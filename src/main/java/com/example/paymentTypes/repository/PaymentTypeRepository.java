package com.example.paymentTypes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.paymentTypes.pojo.PaymentType;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

}
