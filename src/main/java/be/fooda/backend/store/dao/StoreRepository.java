package be.fooda.backend.store.dao;

import be.fooda.backend.store.model.entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {


    boolean existsByTitleAndContactId(String title, Long contactId);

    //boolean existsByTitleAndAddressId(String title, Long addressId);
}