package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}