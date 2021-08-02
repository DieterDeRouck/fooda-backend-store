package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}