package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    boolean existsByNameAndAddress_AddressId(String name, Long addressId);
}