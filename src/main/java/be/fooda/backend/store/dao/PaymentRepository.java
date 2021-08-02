package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}