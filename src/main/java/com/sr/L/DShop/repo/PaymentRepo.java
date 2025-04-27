package com.sr.L.DShop.repo;

import com.sr.L.DShop.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
