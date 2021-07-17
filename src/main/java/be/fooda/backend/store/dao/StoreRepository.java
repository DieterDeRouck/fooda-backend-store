package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreEntity, UUID> {
    boolean existsByNameAndAddress_AddressId(String name, UUID addressId);
}