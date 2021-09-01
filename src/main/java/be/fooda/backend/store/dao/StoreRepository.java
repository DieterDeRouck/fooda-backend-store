package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.StoreEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    List<StoreEntity> findAllByIsActive(Boolean isActive, Pageable pageable);

    Optional<StoreEntity> findByTitleAndAddressId(String title, Long storeId);

    boolean existsByTitleAndContactId(String title, Long contactId);

    @Modifying
    @Query("update stores s set s.isActive = false where s.id = :storeId")
    int makePassive(@Param("storeId") Long storeId);

    boolean existsByTitleAndAddressId(String title, Long addressId);
}